package com.ks.dto;

import java.io.Serializable;

public class ExamRollUserAnswerKey implements Serializable {
    private String userAnswerId;

    private String userId;

    private String courseId;

    private static final long serialVersionUID = 1L;

    public String getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(String userAnswerId) {
        this.userAnswerId = userAnswerId == null ? null : userAnswerId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }
}