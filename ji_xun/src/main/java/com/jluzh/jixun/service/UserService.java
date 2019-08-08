package com.jluzh.jixun.service;

import com.jluzh.model.UserMember;

public interface UserService {

	/**
     * 根据用户名获取用户
     */
	public UserMember getByUsername(String username);
	/**
     * 获取当前登录会员
     */
	public UserMember getCurrentUser();
	
	/**
	 * 根据OpenID获取用户
	 */
	public UserMember getByOpenID(String OpenID);
	
	/**
	 * 自动为来自小程序用户生成用户
	 * @throws Exception 
	 */
	public UserMember autoGenerateUser(String openID) throws Exception;
	
	
	/**
	 * 更新微信用户的相关信息
	 * @param nickName
	 * @param avatarUrl
	 * @return
	 */
	public Integer updataWxUserInfo(String nickName,String avatarUrl);
	
}
