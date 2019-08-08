package com.jluzh.jixun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import com.jluzh.jixun.component.*;
import com.jluzh.jixun.domain.MemberDetails;
import com.jluzh.jixun.service.UserService;
import com.jluzh.model.UserMember;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SpringSercurityConfig extends WebSecurityConfigurerAdapter{
	
		@Autowired
		UserService userService;
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			WxCodeAuthenticationSecurityConfig wxCodeAuthenticationConfig = new WxCodeAuthenticationSecurityConfig();
			http.authorizeRequests()
            .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/*.jpg",
                    "/*.png"
            )
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS)
            .permitAll()
            .antMatchers(
                    "/sso/*",//登录注册
                    "/WxCodeLogin",//小程序登录
                    "/index/**",//首页接口
                    "/product/**",
                    "/esproduct/**"
                    
            )
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new GoAccessDeniedHandler())
            .authenticationEntryPoint(new GoAuthenticationEntryPoint())
            .and()
            .formLogin()
            .loginPage("/sso/login")
            .successHandler(new GoAuthenticationSuccessHandler())
            .failureHandler(new GoAuthenticationFailureHandler())
            .and()
            .logout()
            .logoutUrl("/sso/logout")
            .logoutSuccessHandler(new GoLogoutSuccessHandler())
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .and()
            .csrf()
            .disable()
            .apply(wxCodeAuthenticationConfig);
		}
	
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//	        .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("TEST");
			auth.userDetailsService(userDetailsService())
				.passwordEncoder(new BCryptPasswordEncoder());
				
		}
		@Bean
		public UserDetailsService userDetailsService() {
			
			return new UserDetailsService() {
				
				@Override
				public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
					UserMember user = userService.getByUsername(arg0);
					if(user!=null){
						return new MemberDetails(user);
					}
					 throw new UsernameNotFoundException("用户名或密码错误");
				}
			};
		}
}
