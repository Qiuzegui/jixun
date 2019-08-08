package com.jluzh.mapper;

import com.jluzh.model.UserMember;
import com.jluzh.model.UserMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMemberMapper {
    int countByExample(UserMemberExample example);

    int deleteByExample(UserMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMember record);

    int insertSelective(UserMember record);

    List<UserMember> selectByExample(UserMemberExample example);

    UserMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMember record, @Param("example") UserMemberExample example);

    int updateByExample(@Param("record") UserMember record, @Param("example") UserMemberExample example);

    int updateByPrimaryKeySelective(UserMember record);

    int updateByPrimaryKey(UserMember record);
}