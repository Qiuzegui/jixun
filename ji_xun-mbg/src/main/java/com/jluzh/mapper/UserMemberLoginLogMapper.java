package com.jluzh.mapper;

import com.jluzh.model.UserMemberLoginLog;
import com.jluzh.model.UserMemberLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMemberLoginLogMapper {
    int countByExample(UserMemberLoginLogExample example);

    int deleteByExample(UserMemberLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMemberLoginLog record);

    int insertSelective(UserMemberLoginLog record);

    List<UserMemberLoginLog> selectByExample(UserMemberLoginLogExample example);

    UserMemberLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMemberLoginLog record, @Param("example") UserMemberLoginLogExample example);

    int updateByExample(@Param("record") UserMemberLoginLog record, @Param("example") UserMemberLoginLogExample example);

    int updateByPrimaryKeySelective(UserMemberLoginLog record);

    int updateByPrimaryKey(UserMemberLoginLog record);
}