package com.ks.dao;

import com.ks.dto.PublicSysUserRoleExample;
import com.ks.dto.PublicSysUserRoleKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("publicSysUserRoleMapper")
public interface PublicSysUserRoleMapper {
    long countByExample(PublicSysUserRoleExample example);

    int deleteByExample(PublicSysUserRoleExample example);

    int deleteByPrimaryKey(PublicSysUserRoleKey key);

    int insert(PublicSysUserRoleKey record);

    int insertSelective(PublicSysUserRoleKey record);

    List<PublicSysUserRoleKey> selectByExample(PublicSysUserRoleExample example);

    int updateByExampleSelective(@Param("record") PublicSysUserRoleKey record, @Param("example") PublicSysUserRoleExample example);

    int updateByExample(@Param("record") PublicSysUserRoleKey record, @Param("example") PublicSysUserRoleExample example);
}