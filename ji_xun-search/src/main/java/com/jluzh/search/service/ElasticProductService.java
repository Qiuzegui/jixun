package com.jluzh.search.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jluzh.search.domain.ElasticProduct;


public interface ElasticProductService {
	/**
     * 导入所有商品到ES
     */
    int importAll();
    
    /**
     * 根据id删除商品
     */
    void delete(Long id);
    
    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);
    
    /**
     * 创建商品
     */
    ElasticProduct create(Long id);
    
    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<ElasticProduct> search(String keyword, Integer pageNum, Integer pageSize);
    
    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    Page<ElasticProduct> search(String keyword,Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);
    
    /**
     * 根据商品id推荐相关商品
     */
    Page<ElasticProduct> recommend(Long id, Integer pageNum, Integer pageSize);
}
