package com.ks.dto;

import java.util.ArrayList;
import java.util.List;

public class ExamUserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamUserInfoExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andCnNameIsNull() {
            addCriterion("cn_name is null");
            return (Criteria) this;
        }

        public Criteria andCnNameIsNotNull() {
            addCriterion("cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andCnNameEqualTo(String value) {
            addCriterion("cn_name =", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotEqualTo(String value) {
            addCriterion("cn_name <>", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameGreaterThan(String value) {
            addCriterion("cn_name >", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("cn_name >=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLessThan(String value) {
            addCriterion("cn_name <", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLessThanOrEqualTo(String value) {
            addCriterion("cn_name <=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLike(String value) {
            addCriterion("cn_name like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotLike(String value) {
            addCriterion("cn_name not like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameIn(List<String> values) {
            addCriterion("cn_name in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotIn(List<String> values) {
            addCriterion("cn_name not in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameBetween(String value1, String value2) {
            addCriterion("cn_name between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotBetween(String value1, String value2) {
            addCriterion("cn_name not between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(String value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(String value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(String value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(String value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(String value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLike(String value) {
            addCriterion("card_id like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotLike(String value) {
            addCriterion("card_id not like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<String> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<String> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(String value1, String value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(String value1, String value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andV1IsNull() {
            addCriterion("v1 is null");
            return (Criteria) this;
        }

        public Criteria andV1IsNotNull() {
            addCriterion("v1 is not null");
            return (Criteria) this;
        }

        public Criteria andV1EqualTo(String value) {
            addCriterion("v1 =", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotEqualTo(String value) {
            addCriterion("v1 <>", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1GreaterThan(String value) {
            addCriterion("v1 >", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1GreaterThanOrEqualTo(String value) {
            addCriterion("v1 >=", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1LessThan(String value) {
            addCriterion("v1 <", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1LessThanOrEqualTo(String value) {
            addCriterion("v1 <=", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1Like(String value) {
            addCriterion("v1 like", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotLike(String value) {
            addCriterion("v1 not like", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1In(List<String> values) {
            addCriterion("v1 in", values, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotIn(List<String> values) {
            addCriterion("v1 not in", values, "v1");
            return (Criteria) this;
        }

        public Criteria andV1Between(String value1, String value2) {
            addCriterion("v1 between", value1, value2, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotBetween(String value1, String value2) {
            addCriterion("v1 not between", value1, value2, "v1");
            return (Criteria) this;
        }

        public Criteria andV2IsNull() {
            addCriterion("v2 is null");
            return (Criteria) this;
        }

        public Criteria andV2IsNotNull() {
            addCriterion("v2 is not null");
            return (Criteria) this;
        }

        public Criteria andV2EqualTo(String value) {
            addCriterion("v2 =", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotEqualTo(String value) {
            addCriterion("v2 <>", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2GreaterThan(String value) {
            addCriterion("v2 >", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2GreaterThanOrEqualTo(String value) {
            addCriterion("v2 >=", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2LessThan(String value) {
            addCriterion("v2 <", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2LessThanOrEqualTo(String value) {
            addCriterion("v2 <=", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2Like(String value) {
            addCriterion("v2 like", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotLike(String value) {
            addCriterion("v2 not like", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2In(List<String> values) {
            addCriterion("v2 in", values, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotIn(List<String> values) {
            addCriterion("v2 not in", values, "v2");
            return (Criteria) this;
        }

        public Criteria andV2Between(String value1, String value2) {
            addCriterion("v2 between", value1, value2, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotBetween(String value1, String value2) {
            addCriterion("v2 not between", value1, value2, "v2");
            return (Criteria) this;
        }

        public Criteria andV3IsNull() {
            addCriterion("v3 is null");
            return (Criteria) this;
        }

        public Criteria andV3IsNotNull() {
            addCriterion("v3 is not null");
            return (Criteria) this;
        }

        public Criteria andV3EqualTo(String value) {
            addCriterion("v3 =", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotEqualTo(String value) {
            addCriterion("v3 <>", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3GreaterThan(String value) {
            addCriterion("v3 >", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3GreaterThanOrEqualTo(String value) {
            addCriterion("v3 >=", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3LessThan(String value) {
            addCriterion("v3 <", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3LessThanOrEqualTo(String value) {
            addCriterion("v3 <=", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3Like(String value) {
            addCriterion("v3 like", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotLike(String value) {
            addCriterion("v3 not like", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3In(List<String> values) {
            addCriterion("v3 in", values, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotIn(List<String> values) {
            addCriterion("v3 not in", values, "v3");
            return (Criteria) this;
        }

        public Criteria andV3Between(String value1, String value2) {
            addCriterion("v3 between", value1, value2, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotBetween(String value1, String value2) {
            addCriterion("v3 not between", value1, value2, "v3");
            return (Criteria) this;
        }

        public Criteria andV4IsNull() {
            addCriterion("v4 is null");
            return (Criteria) this;
        }

        public Criteria andV4IsNotNull() {
            addCriterion("v4 is not null");
            return (Criteria) this;
        }

        public Criteria andV4EqualTo(String value) {
            addCriterion("v4 =", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotEqualTo(String value) {
            addCriterion("v4 <>", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4GreaterThan(String value) {
            addCriterion("v4 >", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4GreaterThanOrEqualTo(String value) {
            addCriterion("v4 >=", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4LessThan(String value) {
            addCriterion("v4 <", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4LessThanOrEqualTo(String value) {
            addCriterion("v4 <=", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4Like(String value) {
            addCriterion("v4 like", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotLike(String value) {
            addCriterion("v4 not like", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4In(List<String> values) {
            addCriterion("v4 in", values, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotIn(List<String> values) {
            addCriterion("v4 not in", values, "v4");
            return (Criteria) this;
        }

        public Criteria andV4Between(String value1, String value2) {
            addCriterion("v4 between", value1, value2, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotBetween(String value1, String value2) {
            addCriterion("v4 not between", value1, value2, "v4");
            return (Criteria) this;
        }

        public Criteria andV5IsNull() {
            addCriterion("v5 is null");
            return (Criteria) this;
        }

        public Criteria andV5IsNotNull() {
            addCriterion("v5 is not null");
            return (Criteria) this;
        }

        public Criteria andV5EqualTo(String value) {
            addCriterion("v5 =", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotEqualTo(String value) {
            addCriterion("v5 <>", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5GreaterThan(String value) {
            addCriterion("v5 >", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5GreaterThanOrEqualTo(String value) {
            addCriterion("v5 >=", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5LessThan(String value) {
            addCriterion("v5 <", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5LessThanOrEqualTo(String value) {
            addCriterion("v5 <=", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5Like(String value) {
            addCriterion("v5 like", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotLike(String value) {
            addCriterion("v5 not like", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5In(List<String> values) {
            addCriterion("v5 in", values, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotIn(List<String> values) {
            addCriterion("v5 not in", values, "v5");
            return (Criteria) this;
        }

        public Criteria andV5Between(String value1, String value2) {
            addCriterion("v5 between", value1, value2, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotBetween(String value1, String value2) {
            addCriterion("v5 not between", value1, value2, "v5");
            return (Criteria) this;
        }

        public Criteria andV6IsNull() {
            addCriterion("v6 is null");
            return (Criteria) this;
        }

        public Criteria andV6IsNotNull() {
            addCriterion("v6 is not null");
            return (Criteria) this;
        }

        public Criteria andV6EqualTo(String value) {
            addCriterion("v6 =", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotEqualTo(String value) {
            addCriterion("v6 <>", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6GreaterThan(String value) {
            addCriterion("v6 >", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6GreaterThanOrEqualTo(String value) {
            addCriterion("v6 >=", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6LessThan(String value) {
            addCriterion("v6 <", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6LessThanOrEqualTo(String value) {
            addCriterion("v6 <=", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6Like(String value) {
            addCriterion("v6 like", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotLike(String value) {
            addCriterion("v6 not like", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6In(List<String> values) {
            addCriterion("v6 in", values, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotIn(List<String> values) {
            addCriterion("v6 not in", values, "v6");
            return (Criteria) this;
        }

        public Criteria andV6Between(String value1, String value2) {
            addCriterion("v6 between", value1, value2, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotBetween(String value1, String value2) {
            addCriterion("v6 not between", value1, value2, "v6");
            return (Criteria) this;
        }

        public Criteria andV7IsNull() {
            addCriterion("v7 is null");
            return (Criteria) this;
        }

        public Criteria andV7IsNotNull() {
            addCriterion("v7 is not null");
            return (Criteria) this;
        }

        public Criteria andV7EqualTo(String value) {
            addCriterion("v7 =", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotEqualTo(String value) {
            addCriterion("v7 <>", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7GreaterThan(String value) {
            addCriterion("v7 >", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7GreaterThanOrEqualTo(String value) {
            addCriterion("v7 >=", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7LessThan(String value) {
            addCriterion("v7 <", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7LessThanOrEqualTo(String value) {
            addCriterion("v7 <=", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7Like(String value) {
            addCriterion("v7 like", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotLike(String value) {
            addCriterion("v7 not like", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7In(List<String> values) {
            addCriterion("v7 in", values, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotIn(List<String> values) {
            addCriterion("v7 not in", values, "v7");
            return (Criteria) this;
        }

        public Criteria andV7Between(String value1, String value2) {
            addCriterion("v7 between", value1, value2, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotBetween(String value1, String value2) {
            addCriterion("v7 not between", value1, value2, "v7");
            return (Criteria) this;
        }

        public Criteria andV8IsNull() {
            addCriterion("v8 is null");
            return (Criteria) this;
        }

        public Criteria andV8IsNotNull() {
            addCriterion("v8 is not null");
            return (Criteria) this;
        }

        public Criteria andV8EqualTo(String value) {
            addCriterion("v8 =", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotEqualTo(String value) {
            addCriterion("v8 <>", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8GreaterThan(String value) {
            addCriterion("v8 >", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8GreaterThanOrEqualTo(String value) {
            addCriterion("v8 >=", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8LessThan(String value) {
            addCriterion("v8 <", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8LessThanOrEqualTo(String value) {
            addCriterion("v8 <=", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8Like(String value) {
            addCriterion("v8 like", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotLike(String value) {
            addCriterion("v8 not like", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8In(List<String> values) {
            addCriterion("v8 in", values, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotIn(List<String> values) {
            addCriterion("v8 not in", values, "v8");
            return (Criteria) this;
        }

        public Criteria andV8Between(String value1, String value2) {
            addCriterion("v8 between", value1, value2, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotBetween(String value1, String value2) {
            addCriterion("v8 not between", value1, value2, "v8");
            return (Criteria) this;
        }

        public Criteria andV9IsNull() {
            addCriterion("v9 is null");
            return (Criteria) this;
        }

        public Criteria andV9IsNotNull() {
            addCriterion("v9 is not null");
            return (Criteria) this;
        }

        public Criteria andV9EqualTo(String value) {
            addCriterion("v9 =", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotEqualTo(String value) {
            addCriterion("v9 <>", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9GreaterThan(String value) {
            addCriterion("v9 >", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9GreaterThanOrEqualTo(String value) {
            addCriterion("v9 >=", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9LessThan(String value) {
            addCriterion("v9 <", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9LessThanOrEqualTo(String value) {
            addCriterion("v9 <=", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9Like(String value) {
            addCriterion("v9 like", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotLike(String value) {
            addCriterion("v9 not like", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9In(List<String> values) {
            addCriterion("v9 in", values, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotIn(List<String> values) {
            addCriterion("v9 not in", values, "v9");
            return (Criteria) this;
        }

        public Criteria andV9Between(String value1, String value2) {
            addCriterion("v9 between", value1, value2, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotBetween(String value1, String value2) {
            addCriterion("v9 not between", value1, value2, "v9");
            return (Criteria) this;
        }

        public Criteria andV10IsNull() {
            addCriterion("v10 is null");
            return (Criteria) this;
        }

        public Criteria andV10IsNotNull() {
            addCriterion("v10 is not null");
            return (Criteria) this;
        }

        public Criteria andV10EqualTo(String value) {
            addCriterion("v10 =", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotEqualTo(String value) {
            addCriterion("v10 <>", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10GreaterThan(String value) {
            addCriterion("v10 >", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10GreaterThanOrEqualTo(String value) {
            addCriterion("v10 >=", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10LessThan(String value) {
            addCriterion("v10 <", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10LessThanOrEqualTo(String value) {
            addCriterion("v10 <=", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10Like(String value) {
            addCriterion("v10 like", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotLike(String value) {
            addCriterion("v10 not like", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10In(List<String> values) {
            addCriterion("v10 in", values, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotIn(List<String> values) {
            addCriterion("v10 not in", values, "v10");
            return (Criteria) this;
        }

        public Criteria andV10Between(String value1, String value2) {
            addCriterion("v10 between", value1, value2, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotBetween(String value1, String value2) {
            addCriterion("v10 not between", value1, value2, "v10");
            return (Criteria) this;
        }

        public Criteria andV11IsNull() {
            addCriterion("v11 is null");
            return (Criteria) this;
        }

        public Criteria andV11IsNotNull() {
            addCriterion("v11 is not null");
            return (Criteria) this;
        }

        public Criteria andV11EqualTo(String value) {
            addCriterion("v11 =", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotEqualTo(String value) {
            addCriterion("v11 <>", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11GreaterThan(String value) {
            addCriterion("v11 >", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11GreaterThanOrEqualTo(String value) {
            addCriterion("v11 >=", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11LessThan(String value) {
            addCriterion("v11 <", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11LessThanOrEqualTo(String value) {
            addCriterion("v11 <=", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11Like(String value) {
            addCriterion("v11 like", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotLike(String value) {
            addCriterion("v11 not like", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11In(List<String> values) {
            addCriterion("v11 in", values, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotIn(List<String> values) {
            addCriterion("v11 not in", values, "v11");
            return (Criteria) this;
        }

        public Criteria andV11Between(String value1, String value2) {
            addCriterion("v11 between", value1, value2, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotBetween(String value1, String value2) {
            addCriterion("v11 not between", value1, value2, "v11");
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