package com.jluzh.jixun.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.fastjson.JSONObject;
import com.jluzh.jixun.domain.MemberDetails;
import com.jluzh.jixun.service.UserService;
import com.jluzh.jixun.serviceImpl.UserServiceImpl;
import com.jluzh.jixun.util.SpringUtil;
import com.jluzh.jixun.util.WxAppletCodeParseUtil;
import com.jluzh.model.UserMember;

public class WxUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
		UserService userService =(UserServiceImpl)SpringUtil.getBean(UserServiceImpl.class);
		JSONObject openidAndSessionkey = WxAppletCodeParseUtil.getSessionKeyOropenid(code);
		String openid = openidAndSessionkey.getString("openid");
		if(openid==null) return null;
		UserMember user = userService.getByOpenID(openid);
		if(user!=null){
			return new MemberDetails(user);
		}else{
			 try {
				user = userService.autoGenerateUser(openidAndSessionkey.getString("openid"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new MemberDetails(user);
	}

}
