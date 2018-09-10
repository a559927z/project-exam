package com.ks.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExamQuestionBankDto extends BaseDto implements Serializable {
    private Integer id;

    private String title;

    private String answer;

    private String trueAnswer;

    private String jieXi;

    private String note;

    private String questionBankId;

    private String questionBankName;

    private String categoryId;

    private String courseId;

    private String type;

    private Integer isLock;

    private List<ExamTrueAnswer> examTrueAnswerList;


    private static final long serialVersionUID = 1L;


}