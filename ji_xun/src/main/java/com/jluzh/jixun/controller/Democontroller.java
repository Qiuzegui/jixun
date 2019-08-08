package com.jluzh.jixun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jluzh.jixun.redis.RedisService;
import com.jluzh.jixun.redis.UserKey;
import com.jluzh.jixun.result.*;
import com.jluzh.jixun.service.UserService;
import com.jluzh.jixun.util.WxAppletCodeParseUtil;
@Controller
public class Democontroller {
	
//	@Autowired
//	UserService userService;
//	@Autowired
//	RedisService redisService;
//	
	@RequestMapping("/sso/login")
    public String index(Model model,@RequestParam(value = "error", required = false) String error) {
		if (error != null) {
            model.addAttribute("error", "用户名或密码错误");
        }
        return "login";
    }
	@RequestMapping("/user/index")
	@ResponseBody
	public String thymeleaf(Model model){
		return "hello";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Result<Integer> hello(String code,String encryptedData,String iv){
		System.out.println("开始");
		System.out.println(code);
		JSONObject res = WxAppletCodeParseUtil.getSessionKeyOropenid(code);
		System.out.println(res.toString());
		JSONObject res2 = WxAppletCodeParseUtil.getUserInfo(encryptedData, res.getString("session_key"), iv);
		System.out.println(res2.toString());
		return Result.success(1);
	}
//	
//	@RequestMapping("/thymeleaf")
//	public String thymeleaf(Model model){
//		model.addAttribute("name", "Qzg");
//		return "hello";
//	}
//	
//	@RequestMapping("/get")
//	@ResponseBody
//	public Result<User> gbGet(){
//		User user = userService.getByid(1);
//		return Result.success(user);
//	}
//	
//	@RequestMapping("/redis/get")
//	@ResponseBody
//	public Result<String> redisGet(){
//		String str=redisService.get(UserKey.getById,"key1",String.class);
//		return Result.success(str);
//	}
//	
//	@RequestMapping("/redis/set")
//	@ResponseBody
//	public Result<Boolean> redisSet(){
//		boolean res=redisService.set(UserKey.getById,"key1","hello,Qiuzegui");
//		return Result.success(res);
//	}
}	
