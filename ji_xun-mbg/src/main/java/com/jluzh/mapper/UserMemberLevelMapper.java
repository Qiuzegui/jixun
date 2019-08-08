package com.jluzh.mapper;

import com.jluzh.model.UserMemberLevel;
import com.jluzh.model.UserMemberLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMemberLevelMapper {
    int countByExample(UserMemberLevelExample example);

    int deleteByExample(UserMemberLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMemberLevel record);

    int insertSelective(UserMemberLevel record);

    List<UserMemberLevel> selectByExample(UserMemberLevelExample example);

    UserMemberLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMemberLevel record, @Param("example") UserMemberLevelExample example);

    int updateByExample(@Param("record") UserMemberLevel record, @Param("example") UserMemberLevelExample example);

    int updateByPrimaryKeySelective(UserMemberLevel record);

    int updateByPrimaryKey(UserMemberLevel record);
}