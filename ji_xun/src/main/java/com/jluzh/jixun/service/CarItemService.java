package com.jluzh.jixun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jluzh.jixun.domain.WxCartItem;
import com.jluzh.model.CartItem;

public interface CarItemService {
	/**
     * 查询购物车中是否包含该商品，有增加数量，无则添加到购物车
     */
	@Transactional
	int add(CartItem caritem);
	 /**
     * 根据会员编号获取购物车列表
     */
	List<CartItem> getCarList(Long userId);
	/**
	 * 微信小程序端获取用户购物车列表
	 */
	List<WxCartItem> getWxCarList(Long userid);
	/**
     * 修改某个购物车商品的数量
     */
	int modifyItem(Long id , Long memberId,Integer quantity);
	/**
     * 批量删除购物车中的商品
     */
	int delete(Long memberId,List<Long> ids);
	 /**
     * 清空购物车
     */
	int clear(Long memberId);
}
