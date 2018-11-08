package com.ks.dao;

import com.ks.dto.PublicLog;
import com.ks.dto.PublicLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("publicLogMapper")
public interface PublicLogMapper {
    long countByExample(PublicLogExample example);

    int deleteByExample(PublicLogExample example);

    int insert(PublicLog record);

    int insertSelective(PublicLog record);

    List<PublicLog> selectByExampleWithBLOBs(PublicLogExample example);

    List<PublicLog> selectByExample(PublicLogExample example);

    int updateByExampleSelective(@Param("record") PublicLog record, @Param("example") PublicLogExample example);

    int updateByExampleWithBLOBs(@Param("record") PublicLog record, @Param("example") PublicLogExample example);

    int updateByExample(@Param("record") PublicLog record, @Param("example") PublicLogExample example);
}