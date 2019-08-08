package com.jluzh.jixun.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jluzh.jixun.dao.ProductInfoDao;
import com.jluzh.jixun.domain.WxCartItem;
import com.jluzh.jixun.service.CarItemService;
import com.jluzh.jixun.service.UserService;
import com.jluzh.mapper.CartItemMapper;
import com.jluzh.mapper.ProductMapper;
import com.jluzh.model.CartItem;
import com.jluzh.model.CartItemExample;
import com.jluzh.model.Product;
import com.jluzh.model.ProductExample;
import com.jluzh.model.UserMember;

import io.jsonwebtoken.lang.Collections;

@Service
public class CarItemServiceImpl implements CarItemService{
	@Autowired
	 CartItemMapper cartItemMapper;
	@Autowired
	 UserService userService;
	@Autowired
	 ProductInfoDao productInfoDao;
	@Autowired
	 ProductMapper productMapper;
	
	@Override
	public int add(CartItem cartitem) {
		//判断是否有库存
		Integer realStock = productInfoDao.getRealStock(cartitem.getProductId());
		if(realStock<0)return -1;
		
		int res;
		//获取当前用户
		UserMember currUser =  userService.getCurrentUser();
		//根据商品ID获取卖家
		Long seller = productInfoDao.getUserIDbyProductId(cartitem.getId());
		
		//获取商品价格
		ProductExample example = new ProductExample();
		example.createCriteria().andIdEqualTo(cartitem.getProductId());
		List<Product> cur = productMapper.selectByExample(example);
		if(!Collections.isEmpty(cur)){
			cartitem.setPrice(cur.get(0).getPrice());
		}else return -1;
		
		cartitem.setProductUserId(seller);
		cartitem.setMemberId(currUser.getId());
		cartitem.setMemberNickname(currUser.getNickname());
		cartitem.setDeleteStatus(0);

		CartItem CurrCartItem = getCartItem(cartitem);
		if(CurrCartItem==null){
			cartitem.setCreateDate(new Date());
			res=cartItemMapper.insert(cartitem);
		}else{
			cartitem.setModifyDate(new Date());
			CurrCartItem.setQuantity(cartitem.getQuantity()+CurrCartItem.getQuantity());
			res=cartItemMapper.updateByPrimaryKey(CurrCartItem);
		}
		return res;
	}
	/**
     * 根据会员id,商品id和规格获取购物车中商品
     */
	private CartItem getCartItem(CartItem cartItem){
		CartItemExample example = new CartItemExample();
        CartItemExample.Criteria criteria = example.createCriteria().andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId()).andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(cartItem.getSp1())) {
            criteria.andSp1EqualTo(cartItem.getSp1());
        }
        if (!StringUtils.isEmpty(cartItem.getSp2())) {
            criteria.andSp2EqualTo(cartItem.getSp2());
        }
        if (!StringUtils.isEmpty(cartItem.getSp3())) {
            criteria.andSp3EqualTo(cartItem.getSp3());
        }
        List<CartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
	}
	
	@Override
	public List<CartItem> getCarList(Long userId) {
		CartItemExample  example = new CartItemExample();
		example.createCriteria().andDeleteStatusEqualTo(0).andMemberIdEqualTo(userId);
		List<CartItem> list = cartItemMapper.selectByExample(example);
		return list;
	}

	@Override
	public int modifyItem(Long id, Long memberId,Integer quantity) {
		 CartItem cartItem = new CartItem();
	     cartItem.setQuantity(quantity);
	     CartItemExample example = new CartItemExample();
	     example.createCriteria().andDeleteStatusEqualTo(0)
	            .andIdEqualTo(id).andMemberIdEqualTo(memberId);
	     return cartItemMapper.updateByExampleSelective(cartItem, example);
	}

	@Override
	public int delete(Long memberId, List<Long> ids) {
		 	CartItem record = new CartItem();
	        record.setDeleteStatus(1);
	        CartItemExample example = new CartItemExample();
	        example.createCriteria().andIdIn(ids).andMemberIdEqualTo(memberId);
	        return cartItemMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int clear(Long memberId) {
		  CartItem record = new CartItem();
	      record.setDeleteStatus(1);
	      CartItemExample example = new CartItemExample();
	      example.createCriteria().andMemberIdEqualTo(memberId);
	      return cartItemMapper.updateByExampleSelective(record,example);
	}
	@Override
	public List<WxCartItem> getWxCarList(Long userid) {
		List<WxCartItem> res = productInfoDao.getWxCartItemInfo(userid);
		return res;
	}

}
