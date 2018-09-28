package com.ks.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublicUserInfo implements Serializable {
    private String uid;

    private String enName;

    private String cnName;

    private String password;

    private String salt;

    /**
     * 0:正常状态,1：用户被锁定.
     * 删除(用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 0:正常状态,1：用户被锁定.)
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    // 一个用户具有多个角色
    private List<PublicSysRole> roleList;

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.enName + this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}