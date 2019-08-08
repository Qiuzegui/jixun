package com.jluzh.jixun.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.jluzh.jixun.component.GoAuthenticationFailureHandler;
import com.jluzh.jixun.component.GoAuthenticationSuccessHandler;
import com.jluzh.jixun.component.WxCodeAuthenticationFilter;
import com.jluzh.jixun.component.WxCodeAuthenticationProvider;
@Component
public class WxCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	WxCodeAuthenticationFilter wxCodeAuthenticationFilter = new WxCodeAuthenticationFilter();
    	wxCodeAuthenticationFilter.setAuthenticationFailureHandler(new GoAuthenticationFailureHandler());
    	wxCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    	wxCodeAuthenticationFilter.setAuthenticationSuccessHandler(new GoAuthenticationSuccessHandler());
    	WxCodeAuthenticationProvider wxCodeAuthenticationProvider = new WxCodeAuthenticationProvider();
    	http.authenticationProvider(wxCodeAuthenticationProvider)
    		.addFilterBefore(wxCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
    
    
}
