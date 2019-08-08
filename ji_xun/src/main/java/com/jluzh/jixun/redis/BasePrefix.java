package com.jluzh.jixun.redis;

public abstract class BasePrefix implements KeyPrefix{
	
	private int expireSeconds;
	private String prefix;
	
	public int getExpireSeconds() {
		return expireSeconds;
	}
	public BasePrefix(String prifix){
		this.expireSeconds=0;
		this.prefix=prifix;
	}
	
	@Override
	public int expireSeconds() {//默认0代表永不过期
		return 0;
	}

	@Override
	public String getprefix() {
		String className=getClass().getSimpleName();
		return className+this.prefix+":";
	}
	
}
