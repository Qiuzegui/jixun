package com.jluzh.jixun.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jluzh.jixun.util.JWTTokenUtil;
/**
 * JWT登录授权过滤器
 * @author Administrator
 *	TODO 暂时不用
 */
//public class JWTAuthenticationTokenFilter extends OncePerRequestFilter{
//	
//	@Autowired
//    private UserDetailsService userDetailsService;
//	@Autowired
//	private JWTTokenUtil jwtTokenUtil;
//	@Value("${jwt.tokenHeader}")
//    private String tokenHeader;
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//    
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String authHeader = request.getHeader(this.tokenHeader);
//		 if (authHeader != null && authHeader.startsWith(this.tokenHead)){
//			 String authToken = authHeader.substring(this.tokenHead.length());
//	         String username = jwtTokenUtil.getUserNameFromToken(authToken);
//	         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//	                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//	                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	                    //LOGGER.info("authenticated user:{}", username);
//	                    SecurityContextHolder.getContext().setAuthentication(authentication);
//	                }
//	            }
//		 }
//		 filterChain.doFilter(request, response);
//	}
//	
//}
