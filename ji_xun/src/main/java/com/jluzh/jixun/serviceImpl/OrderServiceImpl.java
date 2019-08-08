package com.jluzh.jixun.serviceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.jluzh.jixun.component.OrderCancelSender;
import com.jluzh.jixun.dao.OrderDao;
import com.jluzh.jixun.dao.ProductInfoDao;
import com.jluzh.jixun.domain.ConfirmOrderInfo;
import com.jluzh.jixun.domain.ConfirmOrderInfo.OrderAmout;
import com.jluzh.jixun.domain.OrderDetail;
import com.jluzh.jixun.domain.OrderInfo;
import com.jluzh.jixun.result.CodeMsg;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.CarItemService;
import com.jluzh.jixun.service.OrderService;
import com.jluzh.jixun.service.UserAddressService;
import com.jluzh.jixun.service.UserService;
import com.jluzh.mapper.OrderItemMapper;
import com.jluzh.mapper.OrderMapper;
import com.jluzh.mapper.ProductMapper;
import com.jluzh.model.CartItem;
import com.jluzh.model.Order;
import com.jluzh.model.OrderExample;
import com.jluzh.model.OrderItem;
import com.jluzh.model.OrderItemExample;
import com.jluzh.model.Product;
import com.jluzh.model.UserAddress;
import com.jluzh.model.UserMember;

import io.jsonwebtoken.lang.Collections;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	UserService userService;
	@Autowired
	CarItemService cartItemService;
	@Autowired
	UserAddressService userAddressService;
	@Autowired
	ProductInfoDao productInfoDao;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	OrderDao orderDao;
//	@Autowired
//	OrderCancelSender sender;
	
	@Override
	public ConfirmOrderInfo CreateConfirmOrder() {
		//获取当前用户ID
		UserMember user = userService.getCurrentUser();
		//根据ID获取用户的购物车商品列表
		List<CartItem> list = cartItemService.getCarList(user.getId());
		//根据ID获取用户的收获地址列表
		List<UserAddress> addresslist = userAddressService.getUserAddressList();
		
		ConfirmOrderInfo confirmOrder = new ConfirmOrderInfo();
		confirmOrder.setGoodsList(list);
		confirmOrder.setUserAddressList(addresslist);
		
		//计算订单总金额
		ConfirmOrderInfo.OrderAmout orderAmout = CalculateTotalMoney(list);
		confirmOrder.setCalcAmount(orderAmout);
		return confirmOrder;
	}
	//计算购物车总金额
	private OrderAmout CalculateTotalMoney(List<CartItem> list) {
		ConfirmOrderInfo.OrderAmout res = new OrderAmout();
		BigDecimal total = new BigDecimal("0");
		for(CartItem item : list){
			//每宗商品的总价格
			total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
		res.setTotalAmount(total);
		return res;
	}
	
	@Override
	public Result CreateOrder(OrderInfo info) {
		//存放订单的商品信息
		List<OrderItem> itemList = new ArrayList<>();
		//获取当前用户Id
		UserMember user = userService.getCurrentUser();
		List<CartItem> list = cartItemService.getCarList(user.getId());
		//根据购物车商品列表生成订单
		for(CartItem item : list){
			OrderItem orderItem = new OrderItem();
			
			orderItem.setProductUserid(item.getProductUserId());
			orderItem.setProductId(item.getProductId());
			orderItem.setProductName(item.getProductName());
			orderItem.setProductPic(item.getProductPic());
			orderItem.setProductPrice(item.getPrice());
			orderItem.setProductQuantity(item.getQuantity());
			itemList.add(orderItem);
		}//订单类型：0->正常订单；
		//判断是否有库存 返回无库存的商品名
		 String noStockName = hasStock(itemList); 
		 if(noStockName != null)return Result.error(CodeMsg.NO_ENOUGH);
		 
		//对库存进行锁定
		 lockStock(itemList);
		 
		Order order = new Order();
		//计算订单的总金额
		BigDecimal amonut = CalOrderTotalAmount(itemList);
		
		order.setTotalAmount(amonut);
		order.setPayAmount(amonut);
		
		//转化为订单信息 插入数据库
		order.setMemberId(user.getId());
		order.setMemberUsername(user.getUsername());
		order.setCreateTime(new Date());
		order.setPayType(info.getPayType());
		order.setSourceType(1);
		order.setStatus(0);//订单状态：0->待付款
		order.setOrderType(0);//订单类型：0->正常订单
		
		//获取用户地址 商品状态信息
		UserAddress userSelect = userAddressService.getDetailAddress(info.getUserAddressId());
		order.setReceiverName(userSelect.getName());
		order.setReceiverPhone(userSelect.getPhoneNumber());
		order.setReceiverPostCode(userSelect.getPostCode());
		order.setReceiverCity(userSelect.getCity());
		order.setReceiverProvince(userSelect.getProvince());
		order.setReceiverRegion(userSelect.getRegion());
		order.setReceiverDetailAddress(userSelect.getDetailAddress());
		order.setConfirmStatus(0);//收货状态:0->未确认；1->已确认
		order.setDeleteStatus(0);
		//生成订单号
		order.setOrderSn(generateSn(order));
       
		orderMapper.insert(order);
		
		for(OrderItem item : itemList){
			item.setOrderId(order.getId());
			item.setOrderSn(order.getOrderSn());
			orderItemMapper.insert(item);
		}
		//删除购物车相关商品
		deleteCartItemList(list,user);
		
		return Result.success(order);
	}
	private void deleteCartItemList(List<CartItem> list, UserMember user) {
		List<Long> ids = new ArrayList<>();
		for(CartItem item : list){
			 ids.add(item.getId());
		}
		cartItemService.delete(user.getId(),ids);
	}
	//18位订单号
	private String generateSn(Order order) {
		StringBuilder str = new StringBuilder();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		str.append(date);
        str.append(String.format("%02d",order.getSourceType()));
        str.append(String.format("%02d",order.getPayType()));
        Random rand = new Random();
        String tmp="";
        for(int i=0 ;i<8;i++){
        	tmp+=rand.nextInt(9);  
        }
        str.append(date).append(tmp);
        return str.toString();
	}
	private BigDecimal CalOrderTotalAmount(List<OrderItem> itemList) {
		BigDecimal Amount = new BigDecimal(0);
		for(OrderItem item : itemList){
			Amount.add(item.getProductPrice());
			System.out.println(Amount);//TODO:Amount 为0 但item.getProductPrice()不为0;
		}
		return Amount;
	}
	private String hasStock(List<OrderItem> itemList) {
		for(OrderItem item : itemList){
			Integer realStock = productInfoDao.getRealStock(item.getProductId());
			if(realStock<0){
				//TODO:还需斟酌 将没有库存的商品下架操作放在此处是否合适
				productInfoDao.updataPublish(item.getProductId());
				return item.getProductName();
			}
		}
		return null;
	}
	private void lockStock(List<OrderItem> itemList) {
		for(OrderItem item : itemList){
			Product cur = productMapper.selectByPrimaryKey(item.getProductId());
			cur.setStockLock(cur.getStockLock()+item.getProductQuantity());
			productMapper.updateByPrimaryKeySelective(cur);
		}
	}

	@Override
	public Result<Integer> AfterPay(Long orderId) {
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(1);
		order.setPaymentTime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
		OrderDetail orderDetail = orderDao.getDetail(orderId);
		int res = orderDao.updateStock(orderDetail.getOrderItemList());
		return Result.success(res);
	}
	
	@Override
	public Result<String> CancalDelayOrder() {
		
		List<OrderDetail> timeOutOrders = orderDao.getTimeOutOrders();
		if(Collections.isEmpty(timeOutOrders)){
			return Result.error(CodeMsg.EMPTY_TIMEOUT);
		}
		//修改超时订单的状态
		List<Long> list = new ArrayList<>();
		for(OrderDetail orderitem:timeOutOrders){
			list.add(orderitem.getId());
		}
		
		orderDao.modifyOrderStatus(list, 4);
		for(OrderDetail orderitem:timeOutOrders){
			//解除订单商品库存锁定
			orderDao.releaseStockLock(orderitem.getOrderItemList());
		}
		return Result.success("操作成功");
	}

	@Override
	public void cancelOrder(Long orderId) {
		//获取未付款的订单号为orderId的订单
		OrderExample example = new OrderExample();
		example.createCriteria().andIdEqualTo(orderId).andStatusEqualTo(0);
		List<Order>cancelOrderList = orderMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(cancelOrderList)){
	       return;
	    }
		Order cancelOrder = cancelOrderList.get(0);
		if(cancelOrder!=null){
			//更新订单状态为关闭
			cancelOrder.setStatus(4);
			orderMapper.updateByPrimaryKeySelective(cancelOrder);
			//获取订单号为orderId中的订单商品
			OrderItemExample orderItemExample=new OrderItemExample();
			orderItemExample.createCriteria().andOrderIdEqualTo(orderId);
			List<OrderItem> list = orderItemMapper.selectByExample(orderItemExample);
			//释放锁定库存
			orderDao.releaseStockLock(list);
		}
	}

	@Override
	public void sendMsgToCancelOrder(Long orderId) {
		long ttl = 5*60*1000;//订单支付时间为5分钟
//		sender.sendMsg(orderId, ttl);;
	}
	@Override
	public List<Order> getRencentOrder(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UserMember user = userService.getCurrentUser();
		List<Order> list = orderDao.getOrderList(user.getId());
		return list;
	}
	
}
