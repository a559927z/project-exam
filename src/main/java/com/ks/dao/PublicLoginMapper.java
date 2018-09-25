package com.ks.dao;

import com.ks.dto.PublicLogin;
import com.ks.dto.PublicLoginExample;
import com.ks.dto.PublicLoginKey;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("publicLoginMapper")
public interface PublicLoginMapper {
    long countByExample(PublicLoginExample example);

    int deleteByExample(PublicLoginExample example);

    int deleteByPrimaryKey(PublicLoginKey key);

    int insert(PublicLogin record);

    int insertSelective(PublicLogin record);

    List<PublicLogin> selectByExample(PublicLoginExample example);

    PublicLogin selectByPrimaryKey(PublicLoginKey key);

    int updateByExampleSelective(@Param("record") PublicLogin record, @Param("example") PublicLoginExample example);

    int updateByExample(@Param("record") PublicLogin record, @Param("example") PublicLoginExample example);

    int updateByPrimaryKeySelective(PublicLogin record);

    int updateByPrimaryKey(PublicLogin record);
}