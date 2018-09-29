package com.ks.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.ks.dao.*;
import com.ks.dto.*;
import com.ks.service.PublicPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
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
    private PublicUserInfoMapperExt publicUserInfoMapperExt;

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
        PublicUserInfo userInfo = publicUserInfoList.get(0);
        String uid = userInfo.getUid();
        userInfo.setRoleList(queryRoleList(uid));
        return userInfo;
    }

//    /**
//     * roleList --> permissionList
//     *
//     * @param userId
//     * @return
//     */
//    private List<PublicSysRole> queryRoleList(String userId) {
//        PublicSysUserRoleExample userRoleExample = new PublicSysUserRoleExample();
//        userRoleExample.createCriteria().andUidEqualTo(userId);
//        // 用户<->角色ID集
//        List<PublicSysUserRoleKey> roleIdList
//                = publicSysUserRoleMapper.selectByExample(userRoleExample);
//
//        List<PublicSysRole> rsRoleList = Lists.newArrayList();
//        roleIdList.forEach(n -> {
//            Integer roleId = n.getRoleId();
//
//            PublicSysRoleExample roleExample = new PublicSysRoleExample();
//            roleExample.createCriteria().andIdEqualTo(roleId);
//            List<PublicSysRole> roleDTOList = publicSysRoleMapper.selectByExample(roleExample);
//
//            if (CollectionUtils.isNotEmpty(roleDTOList)) {
//                rsRoleList.add(roleDTOList.get(0));
//            }
//        });
//
//        return rsRoleList;
//    }
//
//    private List<PublicSysPermission> queryPermissionList(List<PublicSysRole> roleList) {
//        roleList.forEach(n -> {
//            Integer id = n.getId();
//
//        });
//
//    }


    /**
     * roleList --> permissionList
     *
     * @param userId
     * @return
     */
    private List<PublicSysRole> queryRoleList(String userId) {
        PublicSysUserRoleExample userRoleExample = new PublicSysUserRoleExample();
        userRoleExample.createCriteria().andUidEqualTo(userId);
        // 用户<->角色ID集
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


    @Override
    public Page<PublicUserInfo> queryByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return publicUserInfoMapperExt.queryByPage();
    }

    public static void main(String[] args) {
        String str = "123456";
        String salt = "admin8d78869f470951332959580424d4bf4f";
        String s = new Md5Hash(str, salt, 2).toString();
        System.out.println(s);
    }

}
