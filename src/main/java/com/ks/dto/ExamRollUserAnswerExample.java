package com.ks.dto;

import java.util.ArrayList;
import java.util.List;

public class ExamRollUserAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamRollUserAnswerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserAnswerIdIsNull() {
            addCriterion("user_answer_id is null");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdIsNotNull() {
            addCriterion("user_answer_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdEqualTo(String value) {
            addCriterion("user_answer_id =", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdNotEqualTo(String value) {
            addCriterion("user_answer_id <>", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdGreaterThan(String value) {
            addCriterion("user_answer_id >", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_answer_id >=", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdLessThan(String value) {
            addCriterion("user_answer_id <", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdLessThanOrEqualTo(String value) {
            addCriterion("user_answer_id <=", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdLike(String value) {
            addCriterion("user_answer_id like", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdNotLike(String value) {
            addCriterion("user_answer_id not like", value, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdIn(List<String> values) {
            addCriterion("user_answer_id in", values, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdNotIn(List<String> values) {
            addCriterion("user_answer_id not in", values, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdBetween(String value1, String value2) {
            addCriterion("user_answer_id between", value1, value2, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIdNotBetween(String value1, String value2) {
            addCriterion("user_answer_id not between", value1, value2, "userAnswerId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(String value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(String value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(String value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(String value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLike(String value) {
            addCriterion("course_id like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotLike(String value) {
            addCriterion("course_id not like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<String> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<String> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(String value1, String value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(String value1, String value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andRollIdIsNull() {
            addCriterion("roll_id is null");
            return (Criteria) this;
        }

        public Criteria andRollIdIsNotNull() {
            addCriterion("roll_id is not null");
            return (Criteria) this;
        }

        public Criteria andRollIdEqualTo(String value) {
            addCriterion("roll_id =", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdNotEqualTo(String value) {
            addCriterion("roll_id <>", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdGreaterThan(String value) {
            addCriterion("roll_id >", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdGreaterThanOrEqualTo(String value) {
            addCriterion("roll_id >=", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdLessThan(String value) {
            addCriterion("roll_id <", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdLessThanOrEqualTo(String value) {
            addCriterion("roll_id <=", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdLike(String value) {
            addCriterion("roll_id like", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdNotLike(String value) {
            addCriterion("roll_id not like", value, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdIn(List<String> values) {
            addCriterion("roll_id in", values, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdNotIn(List<String> values) {
            addCriterion("roll_id not in", values, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdBetween(String value1, String value2) {
            addCriterion("roll_id between", value1, value2, "rollId");
            return (Criteria) this;
        }

        public Criteria andRollIdNotBetween(String value1, String value2) {
            addCriterion("roll_id not between", value1, value2, "rollId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(String value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(String value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(String value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(String value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLike(String value) {
            addCriterion("question_id like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotLike(String value) {
            addCriterion("question_id not like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<String> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<String> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(String value1, String value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(String value1, String value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIsNull() {
            addCriterion("user_answer is null");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIsNotNull() {
            addCriterion("user_answer is not null");
            return (Criteria) this;
        }

        public Criteria andUserAnswerEqualTo(String value) {
            addCriterion("user_answer =", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerNotEqualTo(String value) {
            addCriterion("user_answer <>", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerGreaterThan(String value) {
            addCriterion("user_answer >", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("user_answer >=", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerLessThan(String value) {
            addCriterion("user_answer <", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerLessThanOrEqualTo(String value) {
            addCriterion("user_answer <=", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerLike(String value) {
            addCriterion("user_answer like", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerNotLike(String value) {
            addCriterion("user_answer not like", value, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerIn(List<String> values) {
            addCriterion("user_answer in", values, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerNotIn(List<String> values) {
            addCriterion("user_answer not in", values, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerBetween(String value1, String value2) {
            addCriterion("user_answer between", value1, value2, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andUserAnswerNotBetween(String value1, String value2) {
            addCriterion("user_answer not between", value1, value2, "userAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerIsNull() {
            addCriterion("true_answer is null");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerIsNotNull() {
            addCriterion("true_answer is not null");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerEqualTo(String value) {
            addCriterion("true_answer =", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerNotEqualTo(String value) {
            addCriterion("true_answer <>", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerGreaterThan(String value) {
            addCriterion("true_answer >", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("true_answer >=", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerLessThan(String value) {
            addCriterion("true_answer <", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerLessThanOrEqualTo(String value) {
            addCriterion("true_answer <=", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerLike(String value) {
            addCriterion("true_answer like", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerNotLike(String value) {
            addCriterion("true_answer not like", value, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerIn(List<String> values) {
            addCriterion("true_answer in", values, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerNotIn(List<String> values) {
            addCriterion("true_answer not in", values, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerBetween(String value1, String value2) {
            addCriterion("true_answer between", value1, value2, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTrueAnswerNotBetween(String value1, String value2) {
            addCriterion("true_answer not between", value1, value2, "trueAnswer");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}