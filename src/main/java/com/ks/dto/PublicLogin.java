package com.ks.dto;

import java.io.Serializable;

public class PublicLogin extends PublicLoginKey implements Serializable {
    private String password;

    private Boolean isLock;

    private static final long serialVersionUID = 1L;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }
}