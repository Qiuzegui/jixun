package com.jluzh.jixun.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jluzh.jixun.result.CodeMsg;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.ProductService;
import com.jluzh.jixun.util.OSSClientUtil;
import com.jluzh.model.Product;

@Controller
public class PublishController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/publish",method=RequestMethod.POST)
	@ResponseBody
	public Result<Integer> publishpro(@RequestParam(value="proName",required=true)String proName,@RequestParam(value="proPrice",required=true)BigDecimal proPrice,
									  @RequestParam(value="content",required=true)String content,@RequestParam(value="proWX",required=true)String proWX,
									  @RequestParam(value="count",required=true)Integer count,@RequestParam(value="category",required=true)Long category,
									  @RequestParam(value="imgurl",required=true)String[] imgurl){
		OSSClientUtil util = new OSSClientUtil();
		Product item = new Product();
		item.setName(proName);item.setDescription(content);
		item.setPrice(proPrice);item.setStock(count);
		item.setProductCategoryId(category);
		item.setPic(util.getImgUrl(imgurl[0]));
		item.setProwx(proWX);
		String url="";
    	
		for(int i=1;i<imgurl.length;i++){
	    	String res = util.getImgUrl(imgurl[i]);
			if(i==imgurl.length-1)
				url+=res;
			else
				url+=res+",";
		}
		item.setAlbumPics(url);
		System.out.println(item);
		int res = productService.addProduct(item);
		if(res==1){
			return Result.success(1);
		}else{
			return Result.error(CodeMsg.PUBLISH_ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/mypublish",method=RequestMethod.GET)
	@ResponseBody
	public Result<List<Product>> getMypublic(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
		List<Product> res = productService.getMypub(pageSize, pageNum);
		return Result.pageSuccess(res);
	}
	
	@RequestMapping(value="/destroy")
	@ResponseBody
	public Result<Integer> destoryPorduct(@RequestParam(value="id") Long id){
		int res = productService.destoryProduct(id);
		if(res==1)return Result.success(1);
		else return Result.error(CodeMsg.DESTORY_ERROR);
	}
	
	@RequestMapping(value="/updatapub",method=RequestMethod.POST)
	@ResponseBody
	public Result<Integer> updateProduct(@RequestBody Product product){
		int res = productService.updataProduct(product);
		return Result.success(res);
	}
}
