package com.jluzh.mapper;

import com.jluzh.model.UserAdminLoginLog;
import com.jluzh.model.UserAdminLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAdminLoginLogMapper {
    int countByExample(UserAdminLoginLogExample example);

    int deleteByExample(UserAdminLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAdminLoginLog record);

    int insertSelective(UserAdminLoginLog record);

    List<UserAdminLoginLog> selectByExample(UserAdminLoginLogExample example);

    UserAdminLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAdminLoginLog record, @Param("example") UserAdminLoginLogExample example);

    int updateByExample(@Param("record") UserAdminLoginLog record, @Param("example") UserAdminLoginLogExample example);

    int updateByPrimaryKeySelective(UserAdminLoginLog record);

    int updateByPrimaryKey(UserAdminLoginLog record);
}