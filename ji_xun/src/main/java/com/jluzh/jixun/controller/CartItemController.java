package com.jluzh.jixun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.jixun.domain.WxCartItem;
import com.jluzh.jixun.result.CodeMsg;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.CarItemService;
import com.jluzh.jixun.service.UserService;
import com.jluzh.model.CartItem;

@Controller
@RequestMapping("/cart")
public class CartItemController {
	@Autowired
	CarItemService cartItemService;
	@Autowired
	UserService userService;
	
	//加入购物车
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Integer> add(@RequestBody CartItem cartitem ){
		int res = cartItemService.add(cartitem);
		if(res>0){
			return Result.success(res);
		}if(res==-1){
			return Result.error(CodeMsg.NO_ENOUGH);
		}
		return Result.error(CodeMsg.ADDCAR_FAIL);
	}
	
	//获取用户的购物车
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 @ResponseBody
	 public Result<List<CartItem>> getUserCartItem(){
		 Long userID = userService.getCurrentUser().getId();
		 List<CartItem> list = cartItemService.getCarList(userID);
		 return Result.success(list);
	 }
	 //微信小程序获取用户的购物车
	 @RequestMapping(value = "/wxlist", method = RequestMethod.GET)
	 @ResponseBody
	 public Result<List<WxCartItem>> getWxCartItem(){
		 Long userID = userService.getCurrentUser().getId();
		 List<WxCartItem> list = cartItemService.getWxCarList(userID);
		 return Result.success(list);
	 }
	 //修改购物车商品的数量
	 @RequestMapping(value = "/modify/quantity", method = RequestMethod.GET)
	 @ResponseBody
	 public Result<Integer> modigyItemQuantity(@RequestParam Long id,
             								   @RequestParam Integer quantity){
		 Long userID = userService.getCurrentUser().getId();
		 int res = cartItemService.modifyItem(id, userID,quantity);
		 if(res>0){
			return Result.success(res);
		 }
		 return Result.error(CodeMsg.MODCAR_FAIL);
	 }
	 
	 //批量删除购物车中的商品
	 @RequestMapping(value = "/delete", method = RequestMethod.POST)
	 @ResponseBody
	 public Result<Integer> delete(@RequestParam("ids") List<Long> ids ){
		 Long userID = userService.getCurrentUser().getId();
		 int res = cartItemService.delete(userID, ids);
		 if(res>0){
				return Result.success(res);
		 }
		 return Result.error(CodeMsg.MODCAR_FAIL);
	 }
	 
	 //清空购物车
	 @RequestMapping(value = "/clear", method = RequestMethod.POST)
	 @ResponseBody
	 public Result<Integer> clear(){
		 Long UserID = userService.getCurrentUser().getId();
		 int res = cartItemService.clear(UserID);
		 if(res>0){
			 return Result.success(res);
		 }
		 return Result.error(CodeMsg.MODCAR_FAIL);
	 }
}
