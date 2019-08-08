package com.jluzh.jixun.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class GoAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		arg1.setHeader("Content-Type", "application/json;charset=utf-8");
		arg1.sendError(403);
		arg1.getWriter().print("{\"code\":403,\"message\":\""+"未授权："+arg2.getMessage()+"\"}");
		arg1.getWriter().flush();
	}
	
}
