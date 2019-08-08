package com.jluzh.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jluzh.search.domain.ElasticProduct;

public interface ElasticProductDao {
	List<ElasticProduct> getAllProduct(@Param("id") Long id);
}
