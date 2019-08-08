package com.jluzh.jixun.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jluzh.jixun.dao.ProductInfoDao;
import com.jluzh.jixun.domain.IndexContent;
import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.jixun.service.HomeService;
import com.jluzh.mapper.IndexAdvertiseMapper;
import com.jluzh.mapper.ProductCategoryMapper;
import com.jluzh.mapper.ProductMapper;
import com.jluzh.model.IndexAdvertise;
import com.jluzh.model.IndexAdvertiseExample;
import com.jluzh.model.Product;
import com.jluzh.model.ProductCategory;
import com.jluzh.model.ProductCategoryExample;
import com.jluzh.model.ProductExample;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	IndexAdvertiseMapper indexAdvertiseMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductCategoryMapper productCategoryMapper;
	@Autowired
	ProductInfoDao productInfoDao;
	@Override
	public IndexContent content() {
		IndexContent content = new IndexContent();
		content.setAdvertiseList(getAdvertiseList());
		//content.setNewProductList(getNewProductList());
		return content;
	}

	@Override
	public List<ProductGeneral> getProductList(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		List<ProductGeneral> list =  productInfoDao.getProductList();
		return list;
	}

	@Override
	public List<ProductCategory> getProductCateList(Long parentId) {
		ProductCategoryExample example = new ProductCategoryExample();
		example.createCriteria().andShowStatusEqualTo(1)
        						.andParentIdEqualTo(parentId);
		List<ProductCategory> list =productCategoryMapper.selectByExample(example);
		return list;
	}

	private List<Product> getNewProductList() {
		ProductExample example = new ProductExample();
		example.createCriteria().andPublishStatusEqualTo(1).andNewStatusEqualTo(1);
		List<Product> list = productMapper.selectByExample(example);
		return list;
	}

	private List<IndexAdvertise> getAdvertiseList() {
		IndexAdvertiseExample example = new IndexAdvertiseExample();
		example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(1);
		example.setOrderByClause("sort desc");
		List<IndexAdvertise> list = indexAdvertiseMapper.selectByExample(example);
		return list;
	}
	

	
}
