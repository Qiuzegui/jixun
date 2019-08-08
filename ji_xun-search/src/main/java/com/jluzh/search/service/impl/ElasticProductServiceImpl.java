package com.jluzh.search.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.el.ArrayELResolver;

import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.WeightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jluzh.search.dao.ElasticProductDao;
import com.jluzh.search.domain.ElasticProduct;
import com.jluzh.search.repository.ProductRepository;
import com.jluzh.search.service.ElasticProductService;
@Service
public class ElasticProductServiceImpl implements ElasticProductService{
	
	@Autowired
	ElasticProductDao elasticProductDao;
	@Autowired
	ProductRepository productRep;
	@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
	
	@Override
	public int importAll() {
		// TODO Auto-generated method stub
		List<ElasticProduct> list = elasticProductDao.getAllProduct(null);
		Iterable<ElasticProduct> esProductIterable = productRep.saveAll(list);
		Iterator<ElasticProduct> ite = esProductIterable.iterator();
		int res = 0;
		while(ite.hasNext()){
			res++;
			ite.next();
		}
		return res;
	}

	@Override
	public void delete(Long id) {
		productRep.deleteById(id);
	}

	@Override
	public void delete(List<Long> ids) {
		if(!ids.isEmpty()){
			List<ElasticProduct> list = new ArrayList<ElasticProduct>();
			for(long id:ids){
				ElasticProduct tmp = new ElasticProduct();
				tmp.setId(id);
				list.add(tmp);
			}
			productRep.deleteAll(list);
		}
	}

	@Override
	public Page<ElasticProduct> search(String keyword, Integer pageNum, Integer pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize);
		 return productRep.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
	}

	@Override
	public Page<ElasticProduct> search(String keyword,Long productCategoryId, Integer pageNum,
			Integer pageSize, Integer sort) {
		Pageable pageable = new PageRequest(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        if (productCategoryId != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
                nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[3];
        ScoreFunctionBuilder<WeightBuilder> namescoreFunctionBuilder = new WeightBuilder();
        namescoreFunctionBuilder.setWeight(10);
        QueryBuilder termQuery1 = QueryBuilders.matchQuery("name", keyword);
        FunctionScoreQueryBuilder.FilterFunctionBuilder nname = new FunctionScoreQueryBuilder.FilterFunctionBuilder(termQuery1,namescoreFunctionBuilder);
        filterFunctionBuilders[0] = nname;
        
        ScoreFunctionBuilder<WeightBuilder> titlescoreFunctionBuilder = new WeightBuilder();
        titlescoreFunctionBuilder.setWeight(7);
        QueryBuilder termQuery2 = QueryBuilders.matchQuery("subTitle", keyword);
        FunctionScoreQueryBuilder.FilterFunctionBuilder subTitle = new FunctionScoreQueryBuilder.FilterFunctionBuilder(termQuery2,titlescoreFunctionBuilder);
        filterFunctionBuilders[1] = subTitle;
        
        ScoreFunctionBuilder<WeightBuilder> keyscoreFunctionBuilder = new WeightBuilder();
        keyscoreFunctionBuilder.setWeight(5);
        QueryBuilder termQuery3 = QueryBuilders.matchQuery("keywords", keyword);
        FunctionScoreQueryBuilder.FilterFunctionBuilder key = new FunctionScoreQueryBuilder.FilterFunctionBuilder(termQuery3,keyscoreFunctionBuilder);
        filterFunctionBuilders[2] = key;
        
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(filterFunctionBuilders).scoreMode(ScoreMode.SUM).setMinScore(2);
        
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }else if(sort==2){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(sort==3){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }

        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        System.out.println(searchQuery.getQuery().toString());
        return productRep.search(searchQuery);
        //return null;
	}

	@Override
	public Page<ElasticProduct> recommend(Long id, Integer pageNum, Integer pageSize) {
		//TODO:待完成
		return null;
	}

	@Override
	public ElasticProduct create(Long id) {
		ElasticProduct res = null;
		List<ElasticProduct> ProductList = elasticProductDao.getAllProduct(id);
		if(!ProductList.isEmpty()){
			ElasticProduct tmp = ProductList.get(0);
			res = productRep.save(tmp);
		}
		return res;
	}
	

}
