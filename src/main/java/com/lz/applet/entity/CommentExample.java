package com.lz.applet.entity;

import java.util.ArrayList;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public CommentExample() {
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

        public Criteria andCIdIsNull() {
            addCriterion("c_id is null");
            return (Criteria) this;
        }

        public Criteria andCIdIsNotNull() {
            addCriterion("c_id is not null");
            return (Criteria) this;
        }

        public Criteria andCIdEqualTo(Integer value) {
            addCriterion("c_id =", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotEqualTo(Integer value) {
            addCriterion("c_id <>", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdGreaterThan(Integer value) {
            addCriterion("c_id >", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_id >=", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdLessThan(Integer value) {
            addCriterion("c_id <", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdLessThanOrEqualTo(Integer value) {
            addCriterion("c_id <=", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdIn(List<Integer> values) {
            addCriterion("c_id in", values, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotIn(List<Integer> values) {
            addCriterion("c_id not in", values, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdBetween(Integer value1, Integer value2) {
            addCriterion("c_id between", value1, value2, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotBetween(Integer value1, Integer value2) {
            addCriterion("c_id not between", value1, value2, "cId");
            return (Criteria) this;
        }

        public Criteria andCUseridIsNull() {
            addCriterion("c_userid is null");
            return (Criteria) this;
        }

        public Criteria andCUseridIsNotNull() {
            addCriterion("c_userid is not null");
            return (Criteria) this;
        }

        public Criteria andCUseridEqualTo(String value) {
            addCriterion("c_userid =", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridNotEqualTo(String value) {
            addCriterion("c_userid <>", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridGreaterThan(String value) {
            addCriterion("c_userid >", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridGreaterThanOrEqualTo(String value) {
            addCriterion("c_userid >=", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridLessThan(String value) {
            addCriterion("c_userid <", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridLessThanOrEqualTo(String value) {
            addCriterion("c_userid <=", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridLike(String value) {
            addCriterion("c_userid like", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridNotLike(String value) {
            addCriterion("c_userid not like", value, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridIn(List<String> values) {
            addCriterion("c_userid in", values, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridNotIn(List<String> values) {
            addCriterion("c_userid not in", values, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridBetween(String value1, String value2) {
            addCriterion("c_userid between", value1, value2, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCUseridNotBetween(String value1, String value2) {
            addCriterion("c_userid not between", value1, value2, "cUserid");
            return (Criteria) this;
        }

        public Criteria andCNameIsNull() {
            addCriterion("c_name is null");
            return (Criteria) this;
        }

        public Criteria andCNameIsNotNull() {
            addCriterion("c_name is not null");
            return (Criteria) this;
        }

        public Criteria andCNameEqualTo(String value) {
            addCriterion("c_name =", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotEqualTo(String value) {
            addCriterion("c_name <>", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameGreaterThan(String value) {
            addCriterion("c_name >", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameGreaterThanOrEqualTo(String value) {
            addCriterion("c_name >=", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLessThan(String value) {
            addCriterion("c_name <", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLessThanOrEqualTo(String value) {
            addCriterion("c_name <=", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLike(String value) {
            addCriterion("c_name like", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotLike(String value) {
            addCriterion("c_name not like", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameIn(List<String> values) {
            addCriterion("c_name in", values, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotIn(List<String> values) {
            addCriterion("c_name not in", values, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameBetween(String value1, String value2) {
            addCriterion("c_name between", value1, value2, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotBetween(String value1, String value2) {
            addCriterion("c_name not between", value1, value2, "cName");
            return (Criteria) this;
        }

        public Criteria andCContentidIsNull() {
            addCriterion("c_contentid is null");
            return (Criteria) this;
        }

        public Criteria andCContentidIsNotNull() {
            addCriterion("c_contentid is not null");
            return (Criteria) this;
        }

        public Criteria andCContentidEqualTo(Integer value) {
            addCriterion("c_contentid =", value, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidNotEqualTo(Integer value) {
            addCriterion("c_contentid <>", value, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidGreaterThan(Integer value) {
            addCriterion("c_contentid >", value, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_contentid >=", value, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidLessThan(Integer value) {
            addCriterion("c_contentid <", value, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidLessThanOrEqualTo(Integer value) {
            addCriterion("c_contentid <=", value, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidIn(List<Integer> values) {
            addCriterion("c_contentid in", values, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidNotIn(List<Integer> values) {
            addCriterion("c_contentid not in", values, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidBetween(Integer value1, Integer value2) {
            addCriterion("c_contentid between", value1, value2, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCContentidNotBetween(Integer value1, Integer value2) {
            addCriterion("c_contentid not between", value1, value2, "cContentid");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeIsNull() {
            addCriterion("c_createtime is null");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeIsNotNull() {
            addCriterion("c_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeEqualTo(String value) {
            addCriterion("c_createtime =", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeNotEqualTo(String value) {
            addCriterion("c_createtime <>", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeGreaterThan(String value) {
            addCriterion("c_createtime >", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("c_createtime >=", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeLessThan(String value) {
            addCriterion("c_createtime <", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("c_createtime <=", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeLike(String value) {
            addCriterion("c_createtime like", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeNotLike(String value) {
            addCriterion("c_createtime not like", value, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeIn(List<String> values) {
            addCriterion("c_createtime in", values, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeNotIn(List<String> values) {
            addCriterion("c_createtime not in", values, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeBetween(String value1, String value2) {
            addCriterion("c_createtime between", value1, value2, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCCreatetimeNotBetween(String value1, String value2) {
            addCriterion("c_createtime not between", value1, value2, "cCreatetime");
            return (Criteria) this;
        }

        public Criteria andCContentIsNull() {
            addCriterion("c_content is null");
            return (Criteria) this;
        }

        public Criteria andCContentIsNotNull() {
            addCriterion("c_content is not null");
            return (Criteria) this;
        }

        public Criteria andCContentEqualTo(String value) {
            addCriterion("c_content =", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentNotEqualTo(String value) {
            addCriterion("c_content <>", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentGreaterThan(String value) {
            addCriterion("c_content >", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentGreaterThanOrEqualTo(String value) {
            addCriterion("c_content >=", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentLessThan(String value) {
            addCriterion("c_content <", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentLessThanOrEqualTo(String value) {
            addCriterion("c_content <=", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentLike(String value) {
            addCriterion("c_content like", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentNotLike(String value) {
            addCriterion("c_content not like", value, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentIn(List<String> values) {
            addCriterion("c_content in", values, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentNotIn(List<String> values) {
            addCriterion("c_content not in", values, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentBetween(String value1, String value2) {
            addCriterion("c_content between", value1, value2, "cContent");
            return (Criteria) this;
        }

        public Criteria andCContentNotBetween(String value1, String value2) {
            addCriterion("c_content not between", value1, value2, "cContent");
            return (Criteria) this;
        }

        public Criteria andCOtheridIsNull() {
            addCriterion("c_otherid is null");
            return (Criteria) this;
        }

        public Criteria andCOtheridIsNotNull() {
            addCriterion("c_otherid is not null");
            return (Criteria) this;
        }

        public Criteria andCOtheridEqualTo(String value) {
            addCriterion("c_otherid =", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridNotEqualTo(String value) {
            addCriterion("c_otherid <>", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridGreaterThan(String value) {
            addCriterion("c_otherid >", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridGreaterThanOrEqualTo(String value) {
            addCriterion("c_otherid >=", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridLessThan(String value) {
            addCriterion("c_otherid <", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridLessThanOrEqualTo(String value) {
            addCriterion("c_otherid <=", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridLike(String value) {
            addCriterion("c_otherid like", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridNotLike(String value) {
            addCriterion("c_otherid not like", value, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridIn(List<String> values) {
            addCriterion("c_otherid in", values, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridNotIn(List<String> values) {
            addCriterion("c_otherid not in", values, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridBetween(String value1, String value2) {
            addCriterion("c_otherid between", value1, value2, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCOtheridNotBetween(String value1, String value2) {
            addCriterion("c_otherid not between", value1, value2, "cOtherid");
            return (Criteria) this;
        }

        public Criteria andCStateIsNull() {
            addCriterion("c_state is null");
            return (Criteria) this;
        }

        public Criteria andCStateIsNotNull() {
            addCriterion("c_state is not null");
            return (Criteria) this;
        }

        public Criteria andCStateEqualTo(Integer value) {
            addCriterion("c_state =", value, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateNotEqualTo(Integer value) {
            addCriterion("c_state <>", value, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateGreaterThan(Integer value) {
            addCriterion("c_state >", value, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_state >=", value, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateLessThan(Integer value) {
            addCriterion("c_state <", value, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateLessThanOrEqualTo(Integer value) {
            addCriterion("c_state <=", value, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateIn(List<Integer> values) {
            addCriterion("c_state in", values, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateNotIn(List<Integer> values) {
            addCriterion("c_state not in", values, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateBetween(Integer value1, Integer value2) {
            addCriterion("c_state between", value1, value2, "cState");
            return (Criteria) this;
        }

        public Criteria andCStateNotBetween(Integer value1, Integer value2) {
            addCriterion("c_state not between", value1, value2, "cState");
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