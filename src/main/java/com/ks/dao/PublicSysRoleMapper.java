package com.ks.dao;

import com.ks.dto.PublicSysRole;
import com.ks.dto.PublicSysRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("publicSysRoleMapper")
public interface PublicSysRoleMapper {
    long countByExample(PublicSysRoleExample example);

    int deleteByExample(PublicSysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PublicSysRole record);

    int insertSelective(PublicSysRole record);

    List<PublicSysRole> selectByExample(PublicSysRoleExample example);

    PublicSysRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PublicSysRole record, @Param("example") PublicSysRoleExample example);

    int updateByExample(@Param("record") PublicSysRole record, @Param("example") PublicSysRoleExample example);

    int updateByPrimaryKeySelective(PublicSysRole record);

    int updateByPrimaryKey(PublicSysRole record);
}