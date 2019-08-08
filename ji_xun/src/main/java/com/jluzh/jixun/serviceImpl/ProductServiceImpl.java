package com.jluzh.jixun.serviceImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jluzh.jixun.dao.ProductInfoDao;
import com.jluzh.jixun.domain.ProductGeneral;
import com.jluzh.jixun.service.ElasticProductService;
import com.jluzh.jixun.service.ProductService;
import com.jluzh.jixun.service.UserService;
import com.jluzh.mapper.ProductMapper;
import com.jluzh.model.Product;
import com.jluzh.model.ProductExample;
import com.jluzh.model.UserMember;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductInfoDao productInfoDao;
	@Autowired
	UserService userService;
	@Autowired
	ElasticProductService elasticProductService;
	
	@Override
	public Product getProductById(Long id) {
		ProductExample example = new ProductExample();
		example.createCriteria().andIdEqualTo(id);
		List<Product> res = productMapper.selectByExample(example);
		if(res!=null){
			return res.get(0);
		}
		return null;
	}

	@Override
	public List<ProductGeneral> getPrductByCateId(Long cateid,Integer type,Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		List<ProductGeneral> res = productInfoDao.getListByCateId(cateid);
		if(type==1){//升序
			Collections.sort(res,new Comparator<ProductGeneral>() {

				@Override
				public int compare(ProductGeneral o1, ProductGeneral o2) {
					return o1.getPrice().subtract(o2.getPrice()).toBigInteger().intValue();
				}
			});
		}else if(type==2){
			Collections.sort(res,new Comparator<ProductGeneral>() {

				@Override
				public int compare(ProductGeneral o1, ProductGeneral o2) {
					return o2.getPrice().subtract(o1.getPrice()).toBigInteger().intValue();
				}
			});
		}
		return res;
	}

	@Override
	public Integer addProduct(Product item) {
		UserMember curr = userService.getCurrentUser();
		item.setTime(new Date());
		item.setUserId(curr.getId());
		item.setPublishStatus(1);
		item.setNewStatus(1);
		item.setVerifyStatus(1);
		item.setRecommandStatus(1);
		item.setDeleteStatus(0);
		item.setStockLock(0);
		int res = productMapper.insert(item);
		elasticProductService.create(item.getId());
		return res;
	}

	@Override
	public List<Product> getMypub(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UserMember user = userService.getCurrentUser();
		ProductExample example = new ProductExample();
		example.createCriteria().andUserIdEqualTo(user.getId());
		List<Product> res = productMapper.selectByExample(example);
		if(res.isEmpty())return null;
		else return res;
	}

	@Override
	public int destoryProduct(Long id) {
		UserMember user = userService.getCurrentUser();
		ProductExample example = new ProductExample();
		example.createCriteria().andUserIdEqualTo(user.getId()).andIdEqualTo(id);
		int res = productMapper.deleteByExample(example);
		return res;
	}

	@Override
	public int updataProduct(Product pro) {
		UserMember user = userService.getCurrentUser();
		Product product = this.getProductById(pro.getId());
		if(product.getUserId()==user.getId()){
			ProductExample example = new ProductExample();
			example.createCriteria().andIdEqualTo(pro.getId());
			int res =productMapper.updateByExampleSelective(pro, example);
			return res;
		}else{
			return 0;
		}
	}

}
