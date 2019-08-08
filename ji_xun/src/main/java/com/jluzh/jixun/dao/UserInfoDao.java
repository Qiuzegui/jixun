package com.jluzh.jixun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoDao {
	@Update("UPDATE user_member SET nickname=#{nickname},icon=#{icon} WHERE id=#{userId}")
	public int updataWxUserInfo(@Param("userId") Long userId,@Param("nickname") String nickname,
								@Param("icon") String icon);
}
