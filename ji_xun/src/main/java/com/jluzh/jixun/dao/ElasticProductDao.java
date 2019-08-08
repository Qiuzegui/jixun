package com.jluzh.jixun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jluzh.jixun.domain.ElasticProduct;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by macro on 2018/6/19.
 */
@Mapper
public interface ElasticProductDao {
    List<ElasticProduct> getAllProduct(@Param("id") Long id);
}
