package com.ks.dto;

import java.io.Serializable;

public class ExamQuestionBankTrueAnswer extends BaseDto implements Serializable {
    private String questionId;

    private String trueAnswerId;

    private String trueAnswer;

    private String questionBankId;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getTrueAnswerId() {
        return trueAnswerId;
    }

    public void setTrueAnswerId(String trueAnswerId) {
        this.trueAnswerId = trueAnswerId == null ? null : trueAnswerId.trim();
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer == null ? null : trueAnswer.trim();
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId == null ? null : questionBankId.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}