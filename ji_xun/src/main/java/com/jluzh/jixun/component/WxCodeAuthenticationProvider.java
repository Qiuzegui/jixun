package com.jluzh.jixun.component;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


public class WxCodeAuthenticationProvider implements AuthenticationProvider{
	private WxUserDetailsService wxUserDetailsService = new WxUserDetailsService();
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		WxCodeAuthenticationToken authenticationToken = (WxCodeAuthenticationToken)authentication;
		UserDetails user = wxUserDetailsService.loadUserByUsername((String)authentication.getPrincipal());
		if(user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
	    }
		WxCodeAuthenticationToken authenticationResult = new WxCodeAuthenticationToken(user,user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		return WxCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
