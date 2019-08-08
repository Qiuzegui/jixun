package com.jluzh.jixun.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class GoAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		arg1.setHeader("Content-Type", "application/json;charset=utf-8");
		arg1.getWriter().print("{\"code\":500,\"message\":\""+"登录失败："+arg2.getMessage()+"\"}");
		arg1.getWriter().flush();
	}
	
}
