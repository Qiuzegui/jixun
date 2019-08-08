package com.jluzh.jixun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.jixun.result.CodeMsg;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.UserAddressService;
import com.jluzh.model.UserAddress;

@Controller
@RequestMapping("/address")
public class UserAddressController {
	@Autowired
	UserAddressService userAddressService;
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public Result<Integer> addAddress(@RequestBody UserAddress address){
		int res = userAddressService.add(address);
		if(res==1)
			return Result.success(res);	
		else return Result.error(CodeMsg.MODIFY_ERROR);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Result<Integer> addAddress(@RequestParam Long id){
		int res = userAddressService.delete(id);
		return Result.success(res);	
	}
	
	@RequestMapping(value="/modify/{id}",method = RequestMethod.POST)
	@ResponseBody
	public Result<Integer> addAddress(@RequestBody UserAddress address,@PathVariable Long id){
		int res = userAddressService.modifyAddress(id, address);
		if(res==1)
			return Result.success(res);	
		else return Result.error(CodeMsg.MODIFY_ERROR);
	}
	
	@RequestMapping(value="/get",method = RequestMethod.GET)
	@ResponseBody
	public Result<List<UserAddress>> getUserAddress(){
		List<UserAddress> res = userAddressService.getUserAddressList();
		return Result.success(res);	
	}
	
	@RequestMapping(value="/getdetail/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Result<UserAddress> getUserAddress(@PathVariable Long id){
		UserAddress res = userAddressService.getDetailAddress(id);
		return Result.success(res);	
	}
}
