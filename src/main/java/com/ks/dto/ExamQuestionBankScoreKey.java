package com.ks.dto;

import java.io.Serializable;

public class ExamQuestionBankScoreKey extends BaseDto implements Serializable {
    private String questionBankId;

    private String questionBankType;

    private static final long serialVersionUID = 1L;

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId == null ? null : questionBankId.trim();
    }

    public String getQuestionBankType() {
        return questionBankType;
    }

    public void setQuestionBankType(String questionBankType) {
        this.questionBankType = questionBankType == null ? null : questionBankType.trim();
    }
}