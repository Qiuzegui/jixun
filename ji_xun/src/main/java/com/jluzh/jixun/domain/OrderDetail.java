package com.jluzh.jixun.domain;

import java.util.List;

import com.jluzh.model.Order;
import com.jluzh.model.OrderItem;

public class OrderDetail extends Order{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OrderItem> orderItemList;

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
}
