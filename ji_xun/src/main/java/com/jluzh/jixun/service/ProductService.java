package com.jluzh.jixun.service;

import java.util.List;

import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.model.Product;

public interface ProductService {
	/**
	 * 根据id获取商品详情
	 */
	public Product getProductById(Long id);
	
	/**
	 * 根据categoryid获取商品列表
	 */
	public List<ProductGeneral>getPrductByCateId(Long cateid,Integer type,Integer pageSize, Integer pageNum);
	
	/**
	 * 用户商品发布
	 */
	public Integer addProduct(Product item);
	
	/**
	 * 获取用户发布的商品
	 */
	
	public List<Product> getMypub(Integer pageSize, Integer pageNum);
	
	/**
	 * 根据商品Id和用户Id删除商品
	 */
	public int destoryProduct(Long id);
	
	public int updataProduct(Product pro);
}
