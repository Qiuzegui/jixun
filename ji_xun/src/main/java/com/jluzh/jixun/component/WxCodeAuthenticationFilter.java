package com.jluzh.jixun.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class WxCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private boolean postOnly = true;
	public WxCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher("/WxCodeLogin", "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest arg0, HttpServletResponse arg1)
			throws AuthenticationException, IOException, ServletException {
		arg0.setCharacterEncoding("UTF-8");
		if (postOnly && !arg0.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + arg0.getMethod());
        }
		String code = obtainCode(arg0);
		System.out.println(code);
		if (code == null) {
			throw new AuthenticationServiceException(
                    "Authentication method not supported : code is empty ");
        }
		code = code.trim();
		WxCodeAuthenticationToken authRequest = new WxCodeAuthenticationToken(code);
		setDetails(arg0, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private String obtainCode(HttpServletRequest request) {
		return request.getParameter("code");
	}
	protected void setDetails(HttpServletRequest request, WxCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
