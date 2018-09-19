package com.ks.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExamQuestionBankReportDto extends BaseDto implements Serializable {

    private String questionNum;


    private String questionBankId;

    private String questionBankName;

    private String categoryId;

    private String courseId;

    private String type;

    private Integer isLock;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private static final long serialVersionUID = 1L;

}