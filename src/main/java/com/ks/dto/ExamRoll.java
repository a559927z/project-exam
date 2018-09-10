package com.ks.dto;

import java.io.Serializable;

public class ExamRoll extends BaseDto implements Serializable {
    private Integer id;

    private String rollId;

    private String questionBankId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRollId() {
        return rollId;
    }

    public void setRollId(String rollId) {
        this.rollId = rollId == null ? null : rollId.trim();
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId == null ? null : questionBankId.trim();
    }
}