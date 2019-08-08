package com.jluzh.jixun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jluzh.jixun.domain.IndexProduct;

@Mapper
public interface IndexDao {
	/**
     * 获取新品
     */
	@Select("select name,pic,price,icon,nickname from product,user_member where product.user_id=user_member.id LIMIT #{offset},#{limit}")
	List<IndexProduct> getNewProduct(@Param("offset") Integer offset,@Param("limit") Integer limit);
	
	/**
     * 获取关注产品
     */
	//List<IndexProduct> getAttentionProduct(@Param("offset") Integer offset,@Param("limit") Integer limit);
	
	/**
     * 获取手机产品
     */
	@Select("select name,pic,price,icon,nickname from product ,user_member where product.user_id=user_member.id and product.product_category_id=13 LIMIT  #{offset},#{limit}")
	List<IndexProduct> getPhoneProduct(@Param("offset") Integer offset,@Param("limit") Integer limit);
	
	/**
     * 获取服装产品
     */
	@Select("select name,pic,price,icon,nickname from product ,user_member where product.user_id=user_member.id and product.product_category_id=6 LIMIT  #{offset},#{limit}")
	List<IndexProduct> getClothProduct(@Param("offset") Integer offset,@Param("limit") Integer limit);
	
	
	/**
     * 获取家电产品
     */
	@Select("select name,pic,price,icon,nickname from product ,user_member where product.user_id=user_member.id and product.product_category_id=21 LIMIT  #{offset},#{limit}")
	List<IndexProduct> getHomeeleProduct(@Param("offset") Integer offset,@Param("limit") Integer limit);
	
	/**
     * 获取汽车产品
     */
	@Select("select name,pic,price,icon,nickname from product ,user_member where product.user_id=user_member.id and product.product_category_id=33 LIMIT  #{offset},#{limit}")
	List<IndexProduct> getCarProduct(@Param("offset") Integer offset,@Param("limit") Integer limit);
	
	
}
