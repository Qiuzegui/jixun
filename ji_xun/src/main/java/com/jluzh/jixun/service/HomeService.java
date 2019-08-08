package com.jluzh.jixun.service;

import java.util.List;

import com.jluzh.jixun.domain.IndexContent;
import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.model.ProductCategory;

public interface HomeService {

	 /**
     * 获取首页内容
     */
    IndexContent content();
    /**
     * 首页商品推荐
     */
    List<ProductGeneral> getProductList(Integer pageSize, Integer pageNum);
    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<ProductCategory> getProductCateList(Long parentId);
    
}
