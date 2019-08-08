package com.jluzh.jixun.domain;

import java.util.List;

import com.jluzh.model.IndexAdvertise;
import com.jluzh.model.Product;

public class IndexContent {
	//首页广告
	private List<IndexAdvertise> advertiseList;
	//新品推荐
	//private List<Product> newProductList;
    
	public List<IndexAdvertise> getAdvertiseList() {
		return advertiseList;
	}
	public void setAdvertiseList(List<IndexAdvertise> advertiseList) {
		this.advertiseList = advertiseList;
	}
//	public List<Product> getNewProductList() {
//		return newProductList;
//	}
//	public void setNewProductList(List<Product> newProductList) {
//		this.newProductList = newProductList;
//	}
    
}
