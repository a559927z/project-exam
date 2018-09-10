package com.ks.dto;

import java.io.Serializable;

public class ExamTrueAnswer extends ExamTrueAnswerKey implements Serializable {
    private String trueAnswer;

    private static final long serialVersionUID = 1L;

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer == null ? null : trueAnswer.trim();
    }
}