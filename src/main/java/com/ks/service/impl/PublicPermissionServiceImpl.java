package com.ks.service.impl;

import com.google.common.collect.Lists;
import com.ks.dao.*;
import com.ks.dto.*;
import com.ks.service.PublicPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月25日 22:03
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service
public class PublicPermissionServiceImpl implements PublicPermissionService {

    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;
    @Autowired
    private PublicSysUserRoleMapper publicSysUserRoleMapper;
    @Autowired
    private PublicSysRoleMapper publicSysRoleMapper;
    @Autowired
    private PublicSysRolePermissionMapper publicSysRolePermissionMapper;
    @Autowired
    private PublicSysPermissionMapper publicSysPermissionMapper;


    @Override
    public PublicUserInfo findByUsername(String username) {
        PublicUserInfoExample userInfoExample = new PublicUserInfoExample();
        userInfoExample.createCriteria().andEnNameEqualTo(username);
        List<PublicUserInfo> publicUserInfoList = publicUserInfoMapper.selectByExample(userInfoExample);

        if (CollectionUtils.isEmpty(publicUserInfoList)) {
            return null;
        }

        PublicUserInfo publicUserInfo = publicUserInfoList.get(0);

        String uid = publicUserInfo.getUid();
        publicUserInfo.setRoleList(queryRoleListByUserId(uid));
        return publicUserInfo;
    }

    private List<PublicSysRole> queryRoleListByUserId(String userId) {
        PublicSysUserRoleExample userRoleExample = new PublicSysUserRoleExample();
        userRoleExample.createCriteria().andUidEqualTo(userId);

        // 用户的角色ID集
        List<PublicSysUserRoleKey> roleIdList
                = publicSysUserRoleMapper.selectByExample(userRoleExample);

        List<PublicSysRole> rs = Lists.newArrayList();
        roleIdList.forEach(n -> {
            Integer roleId = n.getRoleId();

            PublicSysRoleExample roleExample = new PublicSysRoleExample();
            roleExample.createCriteria().andIdEqualTo(roleId);

            List<PublicSysRole> roleDTOList = publicSysRoleMapper.selectByExample(roleExample);

            if (CollectionUtils.isEmpty(roleDTOList)) {
                return;
            }

            //角色下的权限集
            PublicSysRolePermissionExample rolePermissionExample = new PublicSysRolePermissionExample();
            rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
            List<PublicSysRolePermissionKey> permissionIdList = publicSysRolePermissionMapper.selectByExample(rolePermissionExample);

            PublicSysRole roleDTO = roleDTOList.get(0);
            permissionIdList.forEach(j -> {
                Integer permissionId = j.getPermissionId();
                PublicSysPermissionExample permissionExample = new PublicSysPermissionExample();
                permissionExample.createCriteria().andIdEqualTo(permissionId);
                List<PublicSysPermission> permissionDTOList = publicSysPermissionMapper.selectByExample(permissionExample);

                roleDTO.setPermissionList(permissionDTOList);
            });

            rs.add(roleDTO);
        });
        return rs;
    }


}
