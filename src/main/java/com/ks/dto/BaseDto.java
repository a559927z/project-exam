package com.ks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String createdBy = "SYSTEM";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdDate = new Date();


    private String updatedBy = "SYSTEM";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updatedDate = new Date();


}
