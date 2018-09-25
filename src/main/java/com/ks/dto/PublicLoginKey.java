package com.ks.dto;

import java.io.Serializable;

public class PublicLoginKey extends BaseDto implements Serializable {
    private String customerId;

    private String accessId;

    private static final long serialVersionUID = 1L;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId == null ? null : accessId.trim();
    }
}