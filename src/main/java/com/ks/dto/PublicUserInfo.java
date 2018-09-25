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

    private Integer state;

    private static final long serialVersionUID = 1L;

    private List<PublicSysRole> roleList;// 一个用户具有多个角色

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