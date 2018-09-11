package com.ks.dto;

import java.io.Serializable;

public class ExamTrueAnswer extends ExamTrueAnswerKey implements Serializable {
    private String trueAnswer;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer == null ? null : trueAnswer.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}