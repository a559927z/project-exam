package com.ks.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublicSysRole implements Serializable {
    private Integer id;

    private Integer available;

    private String description;

    private String role;

    private static final long serialVersionUID = 1L;

    //角色 -- 权限关系：多对多关系;
    private List<PublicSysPermission> permissionList;
}