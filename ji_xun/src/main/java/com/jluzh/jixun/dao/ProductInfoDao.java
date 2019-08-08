package com.jluzh.jixun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.jixun.domain.WxCartItem;
@Mapper
public interface ProductInfoDao {
	@Select("select user_id  from product where id = #{productId}")
	public Long getUserIDbyProductId(@Param("productId") Long productId);
	
	@Select("select stock - stock_lock  from product where id =#{productId}")
	public Integer getRealStock(@Param("productId") Long productId);
	
	@Update("update product set publish_status=1 where id =#{productId}")
	public Integer updataPublish(@Param("productId") Long productId);
	
	@Select("SELECT c.product_id as id,p.name,p.pic,c.price,c.quantity "+
			"FROM cart_item c left join product p "+
			"ON c.product_id = p.id "+
			"WHERE c.member_id = #{userId}")
	public List<WxCartItem> getWxCartItemInfo(@Param("userId") Long userId);
	
	@Select("SELECT p.id,pic,name,price,u.nickname,time,u.icon from product p,user_member u "+
			"where user_id=u.id and delete_status=0 and publish_status=1 "+
			"and product_category_id=#{cateId} and stock - stock_lock>0")
	public List<ProductGeneral> getListByCateId(@Param("cateId")Long cateId);
	
	@Select("SELECT p.id,pic,name,price,u.nickname,time,u.icon from product p,user_member u "+
			"where user_id=u.id and delete_status=0 and publish_status=1 "+
			"and stock - stock_lock>0")
	public List<ProductGeneral> getProductList();
}
