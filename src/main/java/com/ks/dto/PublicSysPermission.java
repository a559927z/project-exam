package com.ks.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublicSysPermission implements Serializable {
    private Integer id;

    private Integer available;

    private String name;

    private Integer parentId;

    private String parentIds;

    private String permission;

    private String resourceType;

    private String url;

    private static final long serialVersionUID = 1L;

    private List<PublicSysRole> roles;
}