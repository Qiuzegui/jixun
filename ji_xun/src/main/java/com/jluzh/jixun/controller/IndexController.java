package com.jluzh.jixun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.jixun.domain.IndexContent;
import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.HomeService;
import com.jluzh.model.ProductCategory;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	HomeService homeService;
	
	//获取首页内容
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	@ResponseBody
	public Result<IndexContent> content(){
		IndexContent content = homeService.content();
		return Result.success(content);
	}
	
	//获取首页商品分类
	@RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<ProductCategory>> getProductCategory(@PathVariable Long parentId){
		List<ProductCategory> list = homeService.getProductCateList(parentId);
		return Result.success(list);
	}
	
	//分页获取商品
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
    @ResponseBody
	public Result<List<ProductGeneral>> getProductList(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            										@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
		List<ProductGeneral> list = homeService.getProductList(pageSize, pageNum);
		return Result.pageSuccess(list);
	}
	
	
	
}
