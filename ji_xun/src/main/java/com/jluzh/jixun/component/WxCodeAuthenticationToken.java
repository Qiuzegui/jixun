package com.jluzh.jixun.component;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class WxCodeAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private final Object principal;
	
	public WxCodeAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
        super.setAuthenticated(true);
	}
	public WxCodeAuthenticationToken(String code) {
		super(null);
        this.principal = code;
        setAuthenticated(false);
	}
	
	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

}
