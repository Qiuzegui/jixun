package com.jluzh.jixun.redis;

public interface KeyPrefix {
	public int expireSeconds();
	
	public String getprefix();
}
