package com.jluzh.jixun.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
			throws IOException, ServletException {
		arg1.setHeader("Content-Type", "application/json;charset=utf-8");
		arg1.getWriter().print("{\"code\":200,\"message\":\"登录成功\"}");
		arg1.getWriter().flush();
	}
	
}
