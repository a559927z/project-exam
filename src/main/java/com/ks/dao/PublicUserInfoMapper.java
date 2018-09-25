package com.ks.dao;

import com.ks.dto.PublicUserInfo;
import com.ks.dto.PublicUserInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("publicUserInfoMapper")
public interface PublicUserInfoMapper {
    long countByExample(PublicUserInfoExample example);

    int deleteByExample(PublicUserInfoExample example);

    int deleteByPrimaryKey(String uid);

    int insert(PublicUserInfo record);

    int insertSelective(PublicUserInfo record);

    List<PublicUserInfo> selectByExample(PublicUserInfoExample example);

    PublicUserInfo selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") PublicUserInfo record, @Param("example") PublicUserInfoExample example);

    int updateByExample(@Param("record") PublicUserInfo record, @Param("example") PublicUserInfoExample example);

    int updateByPrimaryKeySelective(PublicUserInfo record);

    int updateByPrimaryKey(PublicUserInfo record);
}