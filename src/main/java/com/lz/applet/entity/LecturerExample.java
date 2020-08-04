package com.lz.applet.entity;

import java.util.ArrayList;
import java.util.List;

public class LecturerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public LecturerExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andLecturerNameIsNull() {
            addCriterion("lecturer_name is null");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIsNotNull() {
            addCriterion("lecturer_name is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerNameEqualTo(String value) {
            addCriterion("lecturer_name =", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotEqualTo(String value) {
            addCriterion("lecturer_name <>", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameGreaterThan(String value) {
            addCriterion("lecturer_name >", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_name >=", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLessThan(String value) {
            addCriterion("lecturer_name <", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLessThanOrEqualTo(String value) {
            addCriterion("lecturer_name <=", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLike(String value) {
            addCriterion("lecturer_name like", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotLike(String value) {
            addCriterion("lecturer_name not like", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIn(List<String> values) {
            addCriterion("lecturer_name in", values, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotIn(List<String> values) {
            addCriterion("lecturer_name not in", values, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameBetween(String value1, String value2) {
            addCriterion("lecturer_name between", value1, value2, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotBetween(String value1, String value2) {
            addCriterion("lecturer_name not between", value1, value2, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceIsNull() {
            addCriterion("lecturer_introduce is null");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceIsNotNull() {
            addCriterion("lecturer_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceEqualTo(String value) {
            addCriterion("lecturer_introduce =", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceNotEqualTo(String value) {
            addCriterion("lecturer_introduce <>", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceGreaterThan(String value) {
            addCriterion("lecturer_introduce >", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_introduce >=", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceLessThan(String value) {
            addCriterion("lecturer_introduce <", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceLessThanOrEqualTo(String value) {
            addCriterion("lecturer_introduce <=", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceLike(String value) {
            addCriterion("lecturer_introduce like", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceNotLike(String value) {
            addCriterion("lecturer_introduce not like", value, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceIn(List<String> values) {
            addCriterion("lecturer_introduce in", values, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceNotIn(List<String> values) {
            addCriterion("lecturer_introduce not in", values, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceBetween(String value1, String value2) {
            addCriterion("lecturer_introduce between", value1, value2, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andLecturerIntroduceNotBetween(String value1, String value2) {
            addCriterion("lecturer_introduce not between", value1, value2, "lecturerIntroduce");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsIsNull() {
            addCriterion("`working years` is null");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsIsNotNull() {
            addCriterion("`working years` is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsEqualTo(String value) {
            addCriterion("`working years` =", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsNotEqualTo(String value) {
            addCriterion("`working years` <>", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsGreaterThan(String value) {
            addCriterion("`working years` >", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsGreaterThanOrEqualTo(String value) {
            addCriterion("`working years` >=", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsLessThan(String value) {
            addCriterion("`working years` <", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsLessThanOrEqualTo(String value) {
            addCriterion("`working years` <=", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsLike(String value) {
            addCriterion("`working years` like", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsNotLike(String value) {
            addCriterion("`working years` not like", value, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsIn(List<String> values) {
            addCriterion("`working years` in", values, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsNotIn(List<String> values) {
            addCriterion("`working years` not in", values, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsBetween(String value1, String value2) {
            addCriterion("`working years` between", value1, value2, "workingYears");
            return (Criteria) this;
        }

        public Criteria andWorkingYearsNotBetween(String value1, String value2) {
            addCriterion("`working years` not between", value1, value2, "workingYears");
            return (Criteria) this;
        }

        public Criteria andLecturerPostIsNull() {
            addCriterion("lecturer_post is null");
            return (Criteria) this;
        }

        public Criteria andLecturerPostIsNotNull() {
            addCriterion("lecturer_post is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerPostEqualTo(String value) {
            addCriterion("lecturer_post =", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostNotEqualTo(String value) {
            addCriterion("lecturer_post <>", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostGreaterThan(String value) {
            addCriterion("lecturer_post >", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_post >=", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostLessThan(String value) {
            addCriterion("lecturer_post <", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostLessThanOrEqualTo(String value) {
            addCriterion("lecturer_post <=", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostLike(String value) {
            addCriterion("lecturer_post like", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostNotLike(String value) {
            addCriterion("lecturer_post not like", value, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostIn(List<String> values) {
            addCriterion("lecturer_post in", values, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostNotIn(List<String> values) {
            addCriterion("lecturer_post not in", values, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostBetween(String value1, String value2) {
            addCriterion("lecturer_post between", value1, value2, "lecturerPost");
            return (Criteria) this;
        }

        public Criteria andLecturerPostNotBetween(String value1, String value2) {
            addCriterion("lecturer_post not between", value1, value2, "lecturerPost");
            return (Criteria) this;
        }
    }

    /**
     */
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