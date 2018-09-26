package com.ks.config;


import com.ks.dto.PublicSysPermission;
import com.ks.dto.PublicSysRole;
import com.ks.dto.PublicUserInfo;
import com.ks.service.PublicPermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月25日 21:38
 * @Verdion 1.0 版本
 * ${tags}
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private PublicPermissionService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PublicUserInfo userInfo = (PublicUserInfo) principals.getPrimaryPrincipal();
        for (PublicSysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (PublicSysPermission p : role.getPermissionList()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        PublicUserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo=" + userInfo);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                userInfo,
                //密码
                userInfo.getPassword(),
                //salt=username+salt
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

}