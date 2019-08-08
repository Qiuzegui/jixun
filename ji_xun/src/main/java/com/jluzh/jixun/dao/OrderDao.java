package com.jluzh.jixun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jluzh.jixun.domain.OrderDetail;
import com.jluzh.model.Order;
import com.jluzh.model.OrderItem;

@Mapper
public interface OrderDao {
	@Select("SELECT o.id,o.order_sn,o.coupon_id,o.member_id,"+
			"ot.id ot_id,ot.product_name ot_product_name,ot.product_quantity ot_product_quantity"+
			"FROM `order` o"+
            "LEFT JOIN order_item ot ON o.id = ot.order_id"+
            "WHERE"+
            "o.id = #{orderId}")
	OrderDetail getDetail(@Param("orderId") Long orderId);
	
	Integer updateStock(@Param("itemList") List<OrderItem> itemList);
	
	 /**
     * 获取超时订单
     * @param minute 超时时间（分）
     */
	List<OrderDetail> getTimeOutOrders();
	
	/**
	 * 批量修改订单状态
	 */
	int modifyOrderStatus(@Param("ids") List<Long> ids,@Param("status") Integer status);
	
	/**
	 *解除订单商品库存锁定
	 */
	int releaseStockLock(@Param("itemList") List<OrderItem> orderItemList);
	
	/**
	 * 查询当前用户的订单
	 */
	@Select("SELECT * from `order` WHERE member_id = #{id} ORDER BY create_time desc ")
	List<Order> getOrderList(@Param("id")Long id);
}
