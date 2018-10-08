package com.ks.vo;

import com.ks.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamUserAnswerYaVo extends BaseDto implements Serializable {

    private String userId;

    private String questionBankId;

    private String questionBankName;

    private long total;

    private long finishTotal;

    /**
     * 完成率
     * 完成率（%）= 实际完成数 / 计划完成数 * 100%
     */
    private String finishRate;

    private static final long serialVersionUID = 1L;

}