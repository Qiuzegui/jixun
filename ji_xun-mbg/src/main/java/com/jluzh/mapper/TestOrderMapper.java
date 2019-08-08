package com.jluzh.mapper;

import com.jluzh.model.TestOrder;
import com.jluzh.model.TestOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestOrderMapper {
    int countByExample(TestOrderExample example);

    int deleteByExample(TestOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestOrder record);

    int insertSelective(TestOrder record);

    List<TestOrder> selectByExample(TestOrderExample example);

    TestOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TestOrder record, @Param("example") TestOrderExample example);

    int updateByExample(@Param("record") TestOrder record, @Param("example") TestOrderExample example);

    int updateByPrimaryKeySelective(TestOrder record);

    int updateByPrimaryKey(TestOrder record);
}