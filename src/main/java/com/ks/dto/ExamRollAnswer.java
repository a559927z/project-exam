package com.ks.dto;

import java.io.Serializable;

public class ExamRollAnswer implements Serializable {
    private String id;

    private String questionId;

    private String answer;

    private Boolean isanswer;

    private String rollId;

    private Integer sort;

    private String answerno;

    private String type;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Boolean getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(Boolean isanswer) {
        this.isanswer = isanswer;
    }

    public String getRollId() {
        return rollId;
    }

    public void setRollId(String rollId) {
        this.rollId = rollId == null ? null : rollId.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAnswerno() {
        return answerno;
    }

    public void setAnswerno(String answerno) {
        this.answerno = answerno == null ? null : answerno.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}