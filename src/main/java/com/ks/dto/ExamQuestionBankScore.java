package com.ks.dto;

import java.io.Serializable;

public class ExamQuestionBankScore extends ExamQuestionBankScoreKey implements Serializable {
    private Double score;

    private static final long serialVersionUID = 1L;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}