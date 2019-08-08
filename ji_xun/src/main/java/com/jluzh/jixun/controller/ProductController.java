package com.jluzh.jixun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.jixun.result.CodeMsg;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.ProductService;
import com.jluzh.model.Product;
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	/**
	 * 根据id获取单个商品信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Result<Product> getProduct(@PathVariable Long id){
		Product res = productService.getProductById(id);
		if(res!=null){
			return Result.success(res);
		}else{
			return Result.error(CodeMsg.NO_FOUND);
		}
	}
	/**
	 * 根据分类id获取分页商品列表
	 */
	@RequestMapping(value="/list/{cateid}",method=RequestMethod.GET)
	@ResponseBody
	public Object getProductlist(@PathVariable Long cateid,
			@RequestParam(value = "sorttype", defaultValue = "0") Integer sorttype,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
		List<ProductGeneral> res = productService.getPrductByCateId(cateid, sorttype,pageSize, pageNum);
		return Result.pageSuccess(res);
	}
	
}
