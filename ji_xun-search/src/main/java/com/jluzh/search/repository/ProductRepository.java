package com.jluzh.search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jluzh.search.domain.ElasticProduct;

public interface ProductRepository extends ElasticsearchRepository<ElasticProduct, Long>{
	Page<ElasticProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords,Pageable page);
}
