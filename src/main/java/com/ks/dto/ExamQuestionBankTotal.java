package com.ks.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamQuestionBankTotal extends BaseDto implements Serializable {

    private String questionBankId;

    private String questionBankName;

    /**
     * 总题数
     */
    private Integer total;

}