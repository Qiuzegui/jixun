package com.jluzh.mapper;

import com.jluzh.model.UserAdminRole;
import com.jluzh.model.UserAdminRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAdminRoleMapper {
    int countByExample(UserAdminRoleExample example);

    int deleteByExample(UserAdminRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAdminRole record);

    int insertSelective(UserAdminRole record);

    List<UserAdminRole> selectByExample(UserAdminRoleExample example);

    UserAdminRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAdminRole record, @Param("example") UserAdminRoleExample example);

    int updateByExample(@Param("record") UserAdminRole record, @Param("example") UserAdminRoleExample example);

    int updateByPrimaryKeySelective(UserAdminRole record);

    int updateByPrimaryKey(UserAdminRole record);
}