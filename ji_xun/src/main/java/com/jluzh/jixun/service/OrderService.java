package com.jluzh.jixun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jluzh.jixun.domain.ConfirmOrderInfo;
import com.jluzh.jixun.domain.OrderInfo;
import com.jluzh.jixun.result.Result;
import com.jluzh.model.Order;

public interface OrderService {
	/**
     * 根据用户购物车信息生成确认单信息
     */
	ConfirmOrderInfo CreateConfirmOrder();
	 /**
     * 根据提交信息生成订单
     */
	@Transactional
	Result CreateOrder(OrderInfo info);
	/**
     * 支付成功后的回调
     */
	@Transactional
	Result<Integer> AfterPay(Long orderId);
	 /**
     * 自动取消超时订单
     */
	@Transactional
	Result<String> CancalDelayOrder();
	/**
     * 取消单个超时订单
     */
	@Transactional
    void cancelOrder(Long orderId);
	 /**
     * 发送延迟消息取消订单
     */
	void sendMsgToCancelOrder(Long orderId);
	
	/**
	 * 获取最新订单
	 */
	List<Order>getRencentOrder(Integer pageSize, Integer pageNum);
}
