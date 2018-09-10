package com.ks.dto;

import java.io.Serializable;

public class ExamTrueAnswerKey extends BaseDto implements Serializable {
    private String questionBankId;

    private String trueAnswerId;

    private static final long serialVersionUID = 1L;

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId == null ? null : questionBankId.trim();
    }

    public String getTrueAnswerId() {
        return trueAnswerId;
    }

    public void setTrueAnswerId(String trueAnswerId) {
        this.trueAnswerId = trueAnswerId == null ? null : trueAnswerId.trim();
    }
}