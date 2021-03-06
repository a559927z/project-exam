package com.ks.dto;

import java.io.Serializable;

public class ExamQuestionBankYa extends BaseDto implements Serializable {
    private String questionBankYaId;

    private String questionBankId;

    private String courseId;

    private static final long serialVersionUID = 1L;

    public String getQuestionBankYaId() {
        return questionBankYaId;
    }

    public void setQuestionBankYaId(String questionBankYaId) {
        this.questionBankYaId = questionBankYaId == null ? null : questionBankYaId.trim();
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId == null ? null : questionBankId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }
}