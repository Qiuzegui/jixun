package com.jluzh.jixun.redis;

public class UserKey extends BasePrefix {

	private UserKey(String prifix) {
		super(prifix);
		// TODO Auto-generated constructor stub
	}
	
	public static UserKey getById = new UserKey("id");
	public static UserKey getByName = new UserKey("name");

}
