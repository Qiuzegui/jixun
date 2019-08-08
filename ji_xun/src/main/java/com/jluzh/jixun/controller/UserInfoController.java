package com.jluzh.jixun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.UserService;

@RequestMapping("/userInfo")
@Controller
public class UserInfoController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Result<Integer> updataUserInfo(@RequestParam(required=true) String nickName,@RequestParam(required=true)String avatarUrl){ 
		int res = userService.updataWxUserInfo(nickName, avatarUrl);
		return Result.success(res);
	}
}
