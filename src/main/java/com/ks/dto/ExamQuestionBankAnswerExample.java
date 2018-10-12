package com.ks.dto;

import java.util.ArrayList;
import java.util.List;

public class ExamQuestionBankAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamQuestionBankAnswerExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andIsanswerIsNull() {
            addCriterion("isAnswer is null");
            return (Criteria) this;
        }

        public Criteria andIsanswerIsNotNull() {
            addCriterion("isAnswer is not null");
            return (Criteria) this;
        }

        public Criteria andIsanswerEqualTo(Boolean value) {
            addCriterion("isAnswer =", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotEqualTo(Boolean value) {
            addCriterion("isAnswer <>", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerGreaterThan(Boolean value) {
            addCriterion("isAnswer >", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isAnswer >=", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerLessThan(Boolean value) {
            addCriterion("isAnswer <", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerLessThanOrEqualTo(Boolean value) {
            addCriterion("isAnswer <=", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerIn(List<Boolean> values) {
            addCriterion("isAnswer in", values, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotIn(List<Boolean> values) {
            addCriterion("isAnswer not in", values, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerBetween(Boolean value1, Boolean value2) {
            addCriterion("isAnswer between", value1, value2, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isAnswer not between", value1, value2, "isanswer");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdIsNull() {
            addCriterion("question_bank_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdIsNotNull() {
            addCriterion("question_bank_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdEqualTo(String value) {
            addCriterion("question_bank_id =", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdNotEqualTo(String value) {
            addCriterion("question_bank_id <>", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdGreaterThan(String value) {
            addCriterion("question_bank_id >", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdGreaterThanOrEqualTo(String value) {
            addCriterion("question_bank_id >=", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdLessThan(String value) {
            addCriterion("question_bank_id <", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdLessThanOrEqualTo(String value) {
            addCriterion("question_bank_id <=", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdLike(String value) {
            addCriterion("question_bank_id like", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdNotLike(String value) {
            addCriterion("question_bank_id not like", value, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdIn(List<String> values) {
            addCriterion("question_bank_id in", values, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdNotIn(List<String> values) {
            addCriterion("question_bank_id not in", values, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdBetween(String value1, String value2) {
            addCriterion("question_bank_id between", value1, value2, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andQuestionBankIdNotBetween(String value1, String value2) {
            addCriterion("question_bank_id not between", value1, value2, "questionBankId");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andAnswernoIsNull() {
            addCriterion("answerNo is null");
            return (Criteria) this;
        }

        public Criteria andAnswernoIsNotNull() {
            addCriterion("answerNo is not null");
            return (Criteria) this;
        }

        public Criteria andAnswernoEqualTo(String value) {
            addCriterion("answerNo =", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoNotEqualTo(String value) {
            addCriterion("answerNo <>", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoGreaterThan(String value) {
            addCriterion("answerNo >", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoGreaterThanOrEqualTo(String value) {
            addCriterion("answerNo >=", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoLessThan(String value) {
            addCriterion("answerNo <", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoLessThanOrEqualTo(String value) {
            addCriterion("answerNo <=", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoLike(String value) {
            addCriterion("answerNo like", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoNotLike(String value) {
            addCriterion("answerNo not like", value, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoIn(List<String> values) {
            addCriterion("answerNo in", values, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoNotIn(List<String> values) {
            addCriterion("answerNo not in", values, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoBetween(String value1, String value2) {
            addCriterion("answerNo between", value1, value2, "answerno");
            return (Criteria) this;
        }

        public Criteria andAnswernoNotBetween(String value1, String value2) {
            addCriterion("answerNo not between", value1, value2, "answerno");
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