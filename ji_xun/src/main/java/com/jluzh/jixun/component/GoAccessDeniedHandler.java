package com.jluzh.jixun.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


public class GoAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest arg0, HttpServletResponse arg1, AccessDeniedException arg2)
			throws IOException, ServletException {
		arg1.setHeader("Content-Type", "application/json;charset=utf-8");
		arg1.getWriter().print("{\"code\":401,\"message\":\""+"未认证："+arg2.getMessage()+"\"}");
		arg1.getWriter().flush();
	}
	
}
