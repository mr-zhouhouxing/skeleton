package io.pandora.mall.domian.social;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocialUserInviteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SocialUserInviteExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDisStatusIsNull() {
            addCriterion("dis_status is null");
            return (Criteria) this;
        }

        public Criteria andDisStatusIsNotNull() {
            addCriterion("dis_status is not null");
            return (Criteria) this;
        }

        public Criteria andDisStatusEqualTo(Integer value) {
            addCriterion("dis_status =", value, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusNotEqualTo(Integer value) {
            addCriterion("dis_status <>", value, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusGreaterThan(Integer value) {
            addCriterion("dis_status >", value, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("dis_status >=", value, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusLessThan(Integer value) {
            addCriterion("dis_status <", value, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusLessThanOrEqualTo(Integer value) {
            addCriterion("dis_status <=", value, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusIn(List<Integer> values) {
            addCriterion("dis_status in", values, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusNotIn(List<Integer> values) {
            addCriterion("dis_status not in", values, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusBetween(Integer value1, Integer value2) {
            addCriterion("dis_status between", value1, value2, "disStatus");
            return (Criteria) this;
        }

        public Criteria andDisStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("dis_status not between", value1, value2, "disStatus");
            return (Criteria) this;
        }

        public Criteria andConditionCountIsNull() {
            addCriterion("condition_count is null");
            return (Criteria) this;
        }

        public Criteria andConditionCountIsNotNull() {
            addCriterion("condition_count is not null");
            return (Criteria) this;
        }

        public Criteria andConditionCountEqualTo(Integer value) {
            addCriterion("condition_count =", value, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountNotEqualTo(Integer value) {
            addCriterion("condition_count <>", value, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountGreaterThan(Integer value) {
            addCriterion("condition_count >", value, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("condition_count >=", value, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountLessThan(Integer value) {
            addCriterion("condition_count <", value, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountLessThanOrEqualTo(Integer value) {
            addCriterion("condition_count <=", value, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountIn(List<Integer> values) {
            addCriterion("condition_count in", values, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountNotIn(List<Integer> values) {
            addCriterion("condition_count not in", values, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountBetween(Integer value1, Integer value2) {
            addCriterion("condition_count between", value1, value2, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andConditionCountNotBetween(Integer value1, Integer value2) {
            addCriterion("condition_count not between", value1, value2, "conditionCount");
            return (Criteria) this;
        }

        public Criteria andAwardLockIsNull() {
            addCriterion("award_lock is null");
            return (Criteria) this;
        }

        public Criteria andAwardLockIsNotNull() {
            addCriterion("award_lock is not null");
            return (Criteria) this;
        }

        public Criteria andAwardLockEqualTo(Integer value) {
            addCriterion("award_lock =", value, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockNotEqualTo(Integer value) {
            addCriterion("award_lock <>", value, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockGreaterThan(Integer value) {
            addCriterion("award_lock >", value, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockGreaterThanOrEqualTo(Integer value) {
            addCriterion("award_lock >=", value, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockLessThan(Integer value) {
            addCriterion("award_lock <", value, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockLessThanOrEqualTo(Integer value) {
            addCriterion("award_lock <=", value, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockIn(List<Integer> values) {
            addCriterion("award_lock in", values, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockNotIn(List<Integer> values) {
            addCriterion("award_lock not in", values, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockBetween(Integer value1, Integer value2) {
            addCriterion("award_lock between", value1, value2, "awardLock");
            return (Criteria) this;
        }

        public Criteria andAwardLockNotBetween(Integer value1, Integer value2) {
            addCriterion("award_lock not between", value1, value2, "awardLock");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeIsNull() {
            addCriterion("pass_time is null");
            return (Criteria) this;
        }

        public Criteria andPassTimeIsNotNull() {
            addCriterion("pass_time is not null");
            return (Criteria) this;
        }

        public Criteria andPassTimeEqualTo(Date value) {
            addCriterion("pass_time =", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotEqualTo(Date value) {
            addCriterion("pass_time <>", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeGreaterThan(Date value) {
            addCriterion("pass_time >", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pass_time >=", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeLessThan(Date value) {
            addCriterion("pass_time <", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeLessThanOrEqualTo(Date value) {
            addCriterion("pass_time <=", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeIn(List<Date> values) {
            addCriterion("pass_time in", values, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotIn(List<Date> values) {
            addCriterion("pass_time not in", values, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeBetween(Date value1, Date value2) {
            addCriterion("pass_time between", value1, value2, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotBetween(Date value1, Date value2) {
            addCriterion("pass_time not between", value1, value2, "passTime");
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