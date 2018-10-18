package com.ks.dto;

import java.io.Serializable;

public class ExamUserInfo implements Serializable {
    private String account;

    private String password;

    private String cnName;

    private String cardId;

    private String v1;

    private String v2;

    private String v3;

    private String v4;

    private String v5;

    private String v6;

    private String v7;

    private String v8;

    private String v9;

    private String v10;

    private String v11;

    private static final long serialVersionUID = 1L;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1 == null ? null : v1.trim();
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2 == null ? null : v2.trim();
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3 == null ? null : v3.trim();
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4 == null ? null : v4.trim();
    }

    public String getV5() {
        return v5;
    }

    public void setV5(String v5) {
        this.v5 = v5 == null ? null : v5.trim();
    }

    public String getV6() {
        return v6;
    }

    public void setV6(String v6) {
        this.v6 = v6 == null ? null : v6.trim();
    }

    public String getV7() {
        return v7;
    }

    public void setV7(String v7) {
        this.v7 = v7 == null ? null : v7.trim();
    }

    public String getV8() {
        return v8;
    }

    public void setV8(String v8) {
        this.v8 = v8 == null ? null : v8.trim();
    }

    public String getV9() {
        return v9;
    }

    public void setV9(String v9) {
        this.v9 = v9 == null ? null : v9.trim();
    }

    public String getV10() {
        return v10;
    }

    public void setV10(String v10) {
        this.v10 = v10 == null ? null : v10.trim();
    }

    public String getV11() {
        return v11;
    }

    public void setV11(String v11) {
        this.v11 = v11 == null ? null : v11.trim();
    }
}