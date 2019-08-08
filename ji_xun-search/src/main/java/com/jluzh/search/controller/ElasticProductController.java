package com.jluzh.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.search.domain.ElasticProduct;
import com.jluzh.search.result.Result;
import com.jluzh.search.service.ElasticProductService;

@Controller
@RequestMapping("/esproduct")
public class ElasticProductController {
	
	@Autowired
	private ElasticProductService elasticProductService;
	
	@RequestMapping(value="/importall",method=RequestMethod.GET)
	@ResponseBody
	public Result<Integer> importall(){
		int res = elasticProductService.importAll();
		return Result.success(res);
	}
	
	@RequestMapping(value="/delete{id}",method=RequestMethod.GET)
	@ResponseBody
	public Result<Integer> delete(@PathVariable Long id){
		elasticProductService.delete(id);
		return Result.success(1);
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@ResponseBody
	public Result<Integer> delete(@RequestParam("ids") List<Long> ids){
		elasticProductService.delete(ids);
		return Result.success(1);
	}
	
	@RequestMapping(value="/create{id}",method=RequestMethod.POST)
	@ResponseBody
	public Result<ElasticProduct> create(@PathVariable Long id){
		ElasticProduct res = elasticProductService.create(id);
		return Result.success(res);
	}
	
	@RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> search(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(required = false, defaultValue = "5") Integer pageSize){
		Page<ElasticProduct> ProductPage = elasticProductService.search(keyword, pageNum, pageSize);
		return Result.success(ProductPage);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> search(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false) Long productCategoryId,
                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(required = false, defaultValue = "0") Integer sort){
		Page<ElasticProduct> ProductPage = elasticProductService.search(keyword, productCategoryId, pageNum, pageSize, sort);
		return Result.success(ProductPage);
	}
}
