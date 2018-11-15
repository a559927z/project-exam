package com.ks.dto;

import java.io.Serializable;

public class ExamRollUserAnswer extends ExamRollUserAnswerKey implements Serializable {
    private String rollId;

    private String questionId;

    private String userAnswer;

    private String trueAnswer;

    private String type;

    private Boolean isYes;

    private static final long serialVersionUID = 1L;

    public String getRollId() {
        return rollId;
    }

    public void setRollId(String rollId) {
        this.rollId = rollId == null ? null : rollId.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer == null ? null : userAnswer.trim();
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer == null ? null : trueAnswer.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Boolean getIsYes() {
        return isYes;
    }

    public void setIsYes(Boolean isYes) {
        this.isYes = isYes;
    }
}