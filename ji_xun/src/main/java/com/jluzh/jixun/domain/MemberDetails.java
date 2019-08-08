package com.jluzh.jixun.domain;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jluzh.model.UserMember;

public class MemberDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserMember userMember;

	public MemberDetails(UserMember userMember) {
		super();
		this.userMember = userMember;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 return Arrays.asList(new SimpleGrantedAuthority("TEST"));
	}

	@Override
	public String getPassword() {
		return userMember.getPassword();
	}

	@Override
	public String getUsername() {
		return userMember.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return userMember.getStatus()==1;
	}

	public UserMember getUserMember() {
		return userMember;
	}
	
}
