package com.ks.vo;

import com.ks.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamUserAnswerYaVo extends BaseDto implements Serializable {

    private String userAnswerId;

    private String userId;

    private String questionBankId;

    private long total;

    private long finishTotal;


    private static final long serialVersionUID = 1L;

}