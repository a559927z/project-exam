package com.ks.dao;

import com.ks.dto.PublicSysRolePermissionExample;
import com.ks.dto.PublicSysRolePermissionKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("publicSysRolePermissionMapper")
public interface PublicSysRolePermissionMapper {
    long countByExample(PublicSysRolePermissionExample example);

    int deleteByExample(PublicSysRolePermissionExample example);

    int deleteByPrimaryKey(PublicSysRolePermissionKey key);

    int insert(PublicSysRolePermissionKey record);

    int insertSelective(PublicSysRolePermissionKey record);

    List<PublicSysRolePermissionKey> selectByExample(PublicSysRolePermissionExample example);

    int updateByExampleSelective(@Param("record") PublicSysRolePermissionKey record, @Param("example") PublicSysRolePermissionExample example);

    int updateByExample(@Param("record") PublicSysRolePermissionKey record, @Param("example") PublicSysRolePermissionExample example);
}