package com.jluzh.mapper;

import com.jluzh.model.IndexAdvertise;
import com.jluzh.model.IndexAdvertiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexAdvertiseMapper {
    int countByExample(IndexAdvertiseExample example);

    int deleteByExample(IndexAdvertiseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IndexAdvertise record);

    int insertSelective(IndexAdvertise record);

    List<IndexAdvertise> selectByExample(IndexAdvertiseExample example);

    IndexAdvertise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IndexAdvertise record, @Param("example") IndexAdvertiseExample example);

    int updateByExample(@Param("record") IndexAdvertise record, @Param("example") IndexAdvertiseExample example);

    int updateByPrimaryKeySelective(IndexAdvertise record);

    int updateByPrimaryKey(IndexAdvertise record);
}