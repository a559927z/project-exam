package com.ks.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共基本dto
 *
 * @author jxzhang on 2017年11月13日
 * @Verdion 1.0 版本
 */
@Data
public class BaseDto implements Serializable {

    private static final long serialVersionUID = 104793415528352911L;

    /**
     * 键
     */
    private String k;
    /**
     * 值
     */
    private String v;

    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;

    public BaseDto() {
        super();
    }

    public BaseDto(String k, String v) {
        super();
        this.k = k;
        this.v = v;
    }


}
