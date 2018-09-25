package com.ks.dao;

import com.ks.dto.PublicSysPermission;
import com.ks.dto.PublicSysPermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("publicSysPermissionMapper")
public interface PublicSysPermissionMapper {
    long countByExample(PublicSysPermissionExample example);

    int deleteByExample(PublicSysPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PublicSysPermission record);

    int insertSelective(PublicSysPermission record);

    List<PublicSysPermission> selectByExample(PublicSysPermissionExample example);

    PublicSysPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PublicSysPermission record, @Param("example") PublicSysPermissionExample example);

    int updateByExample(@Param("record") PublicSysPermission record, @Param("example") PublicSysPermissionExample example);

    int updateByPrimaryKeySelective(PublicSysPermission record);

    int updateByPrimaryKey(PublicSysPermission record);
}