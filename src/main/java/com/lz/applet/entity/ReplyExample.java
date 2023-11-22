package com.lz.applet.entity;

import java.util.ArrayList;
import java.util.List;

public class ReplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ReplyExample() {
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

        public Criteria andRIdIsNull() {
            addCriterion("r_id is null");
            return (Criteria) this;
        }

        public Criteria andRIdIsNotNull() {
            addCriterion("r_id is not null");
            return (Criteria) this;
        }

        public Criteria andRIdEqualTo(Integer value) {
            addCriterion("r_id =", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotEqualTo(Integer value) {
            addCriterion("r_id <>", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdGreaterThan(Integer value) {
            addCriterion("r_id >", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("r_id >=", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdLessThan(Integer value) {
            addCriterion("r_id <", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdLessThanOrEqualTo(Integer value) {
            addCriterion("r_id <=", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdIn(List<Integer> values) {
            addCriterion("r_id in", values, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotIn(List<Integer> values) {
            addCriterion("r_id not in", values, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdBetween(Integer value1, Integer value2) {
            addCriterion("r_id between", value1, value2, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotBetween(Integer value1, Integer value2) {
            addCriterion("r_id not between", value1, value2, "rId");
            return (Criteria) this;
        }

        public Criteria andRUseridIsNull() {
            addCriterion("r_userid is null");
            return (Criteria) this;
        }

        public Criteria andRUseridIsNotNull() {
            addCriterion("r_userid is not null");
            return (Criteria) this;
        }

        public Criteria andRUseridEqualTo(String value) {
            addCriterion("r_userid =", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridNotEqualTo(String value) {
            addCriterion("r_userid <>", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridGreaterThan(String value) {
            addCriterion("r_userid >", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridGreaterThanOrEqualTo(String value) {
            addCriterion("r_userid >=", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridLessThan(String value) {
            addCriterion("r_userid <", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridLessThanOrEqualTo(String value) {
            addCriterion("r_userid <=", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridLike(String value) {
            addCriterion("r_userid like", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridNotLike(String value) {
            addCriterion("r_userid not like", value, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridIn(List<String> values) {
            addCriterion("r_userid in", values, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridNotIn(List<String> values) {
            addCriterion("r_userid not in", values, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridBetween(String value1, String value2) {
            addCriterion("r_userid between", value1, value2, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRUseridNotBetween(String value1, String value2) {
            addCriterion("r_userid not between", value1, value2, "rUserid");
            return (Criteria) this;
        }

        public Criteria andRNameIsNull() {
            addCriterion("r_name is null");
            return (Criteria) this;
        }

        public Criteria andRNameIsNotNull() {
            addCriterion("r_name is not null");
            return (Criteria) this;
        }

        public Criteria andRNameEqualTo(String value) {
            addCriterion("r_name =", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotEqualTo(String value) {
            addCriterion("r_name <>", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameGreaterThan(String value) {
            addCriterion("r_name >", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameGreaterThanOrEqualTo(String value) {
            addCriterion("r_name >=", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameLessThan(String value) {
            addCriterion("r_name <", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameLessThanOrEqualTo(String value) {
            addCriterion("r_name <=", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameLike(String value) {
            addCriterion("r_name like", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotLike(String value) {
            addCriterion("r_name not like", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameIn(List<String> values) {
            addCriterion("r_name in", values, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotIn(List<String> values) {
            addCriterion("r_name not in", values, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameBetween(String value1, String value2) {
            addCriterion("r_name between", value1, value2, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotBetween(String value1, String value2) {
            addCriterion("r_name not between", value1, value2, "rName");
            return (Criteria) this;
        }

        public Criteria andRCreatimeIsNull() {
            addCriterion("r_creatime is null");
            return (Criteria) this;
        }

        public Criteria andRCreatimeIsNotNull() {
            addCriterion("r_creatime is not null");
            return (Criteria) this;
        }

        public Criteria andRCreatimeEqualTo(String value) {
            addCriterion("r_creatime =", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeNotEqualTo(String value) {
            addCriterion("r_creatime <>", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeGreaterThan(String value) {
            addCriterion("r_creatime >", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeGreaterThanOrEqualTo(String value) {
            addCriterion("r_creatime >=", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeLessThan(String value) {
            addCriterion("r_creatime <", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeLessThanOrEqualTo(String value) {
            addCriterion("r_creatime <=", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeLike(String value) {
            addCriterion("r_creatime like", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeNotLike(String value) {
            addCriterion("r_creatime not like", value, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeIn(List<String> values) {
            addCriterion("r_creatime in", values, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeNotIn(List<String> values) {
            addCriterion("r_creatime not in", values, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeBetween(String value1, String value2) {
            addCriterion("r_creatime between", value1, value2, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRCreatimeNotBetween(String value1, String value2) {
            addCriterion("r_creatime not between", value1, value2, "rCreatime");
            return (Criteria) this;
        }

        public Criteria andRContentIsNull() {
            addCriterion("r_content is null");
            return (Criteria) this;
        }

        public Criteria andRContentIsNotNull() {
            addCriterion("r_content is not null");
            return (Criteria) this;
        }

        public Criteria andRContentEqualTo(String value) {
            addCriterion("r_content =", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentNotEqualTo(String value) {
            addCriterion("r_content <>", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentGreaterThan(String value) {
            addCriterion("r_content >", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentGreaterThanOrEqualTo(String value) {
            addCriterion("r_content >=", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentLessThan(String value) {
            addCriterion("r_content <", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentLessThanOrEqualTo(String value) {
            addCriterion("r_content <=", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentLike(String value) {
            addCriterion("r_content like", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentNotLike(String value) {
            addCriterion("r_content not like", value, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentIn(List<String> values) {
            addCriterion("r_content in", values, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentNotIn(List<String> values) {
            addCriterion("r_content not in", values, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentBetween(String value1, String value2) {
            addCriterion("r_content between", value1, value2, "rContent");
            return (Criteria) this;
        }

        public Criteria andRContentNotBetween(String value1, String value2) {
            addCriterion("r_content not between", value1, value2, "rContent");
            return (Criteria) this;
        }

        public Criteria andROtheridIsNull() {
            addCriterion("r_otherid is null");
            return (Criteria) this;
        }

        public Criteria andROtheridIsNotNull() {
            addCriterion("r_otherid is not null");
            return (Criteria) this;
        }

        public Criteria andROtheridEqualTo(String value) {
            addCriterion("r_otherid =", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridNotEqualTo(String value) {
            addCriterion("r_otherid <>", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridGreaterThan(String value) {
            addCriterion("r_otherid >", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridGreaterThanOrEqualTo(String value) {
            addCriterion("r_otherid >=", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridLessThan(String value) {
            addCriterion("r_otherid <", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridLessThanOrEqualTo(String value) {
            addCriterion("r_otherid <=", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridLike(String value) {
            addCriterion("r_otherid like", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridNotLike(String value) {
            addCriterion("r_otherid not like", value, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridIn(List<String> values) {
            addCriterion("r_otherid in", values, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridNotIn(List<String> values) {
            addCriterion("r_otherid not in", values, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridBetween(String value1, String value2) {
            addCriterion("r_otherid between", value1, value2, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andROtheridNotBetween(String value1, String value2) {
            addCriterion("r_otherid not between", value1, value2, "rOtherid");
            return (Criteria) this;
        }

        public Criteria andRWordsIsNull() {
            addCriterion("r_words is null");
            return (Criteria) this;
        }

        public Criteria andRWordsIsNotNull() {
            addCriterion("r_words is not null");
            return (Criteria) this;
        }

        public Criteria andRWordsEqualTo(String value) {
            addCriterion("r_words =", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsNotEqualTo(String value) {
            addCriterion("r_words <>", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsGreaterThan(String value) {
            addCriterion("r_words >", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsGreaterThanOrEqualTo(String value) {
            addCriterion("r_words >=", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsLessThan(String value) {
            addCriterion("r_words <", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsLessThanOrEqualTo(String value) {
            addCriterion("r_words <=", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsLike(String value) {
            addCriterion("r_words like", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsNotLike(String value) {
            addCriterion("r_words not like", value, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsIn(List<String> values) {
            addCriterion("r_words in", values, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsNotIn(List<String> values) {
            addCriterion("r_words not in", values, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsBetween(String value1, String value2) {
            addCriterion("r_words between", value1, value2, "rWords");
            return (Criteria) this;
        }

        public Criteria andRWordsNotBetween(String value1, String value2) {
            addCriterion("r_words not between", value1, value2, "rWords");
            return (Criteria) this;
        }

        public Criteria andRContentidIsNull() {
            addCriterion("r_contentid is null");
            return (Criteria) this;
        }

        public Criteria andRContentidIsNotNull() {
            addCriterion("r_contentid is not null");
            return (Criteria) this;
        }

        public Criteria andRContentidEqualTo(Integer value) {
            addCriterion("r_contentid =", value, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidNotEqualTo(Integer value) {
            addCriterion("r_contentid <>", value, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidGreaterThan(Integer value) {
            addCriterion("r_contentid >", value, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("r_contentid >=", value, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidLessThan(Integer value) {
            addCriterion("r_contentid <", value, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidLessThanOrEqualTo(Integer value) {
            addCriterion("r_contentid <=", value, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidIn(List<Integer> values) {
            addCriterion("r_contentid in", values, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidNotIn(List<Integer> values) {
            addCriterion("r_contentid not in", values, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidBetween(Integer value1, Integer value2) {
            addCriterion("r_contentid between", value1, value2, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRContentidNotBetween(Integer value1, Integer value2) {
            addCriterion("r_contentid not between", value1, value2, "rContentid");
            return (Criteria) this;
        }

        public Criteria andRStateIsNull() {
            addCriterion("r_state is null");
            return (Criteria) this;
        }

        public Criteria andRStateIsNotNull() {
            addCriterion("r_state is not null");
            return (Criteria) this;
        }

        public Criteria andRStateEqualTo(Integer value) {
            addCriterion("r_state =", value, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateNotEqualTo(Integer value) {
            addCriterion("r_state <>", value, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateGreaterThan(Integer value) {
            addCriterion("r_state >", value, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("r_state >=", value, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateLessThan(Integer value) {
            addCriterion("r_state <", value, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateLessThanOrEqualTo(Integer value) {
            addCriterion("r_state <=", value, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateIn(List<Integer> values) {
            addCriterion("r_state in", values, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateNotIn(List<Integer> values) {
            addCriterion("r_state not in", values, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateBetween(Integer value1, Integer value2) {
            addCriterion("r_state between", value1, value2, "rState");
            return (Criteria) this;
        }

        public Criteria andRStateNotBetween(Integer value1, Integer value2) {
            addCriterion("r_state not between", value1, value2, "rState");
            return (Criteria) this;
        }

        public Criteria andRRespondentIsNull() {
            addCriterion("r_respondent is null");
            return (Criteria) this;
        }

        public Criteria andRRespondentIsNotNull() {
            addCriterion("r_respondent is not null");
            return (Criteria) this;
        }

        public Criteria andRRespondentEqualTo(String value) {
            addCriterion("r_respondent =", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentNotEqualTo(String value) {
            addCriterion("r_respondent <>", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentGreaterThan(String value) {
            addCriterion("r_respondent >", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentGreaterThanOrEqualTo(String value) {
            addCriterion("r_respondent >=", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentLessThan(String value) {
            addCriterion("r_respondent <", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentLessThanOrEqualTo(String value) {
            addCriterion("r_respondent <=", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentLike(String value) {
            addCriterion("r_respondent like", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentNotLike(String value) {
            addCriterion("r_respondent not like", value, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentIn(List<String> values) {
            addCriterion("r_respondent in", values, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentNotIn(List<String> values) {
            addCriterion("r_respondent not in", values, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentBetween(String value1, String value2) {
            addCriterion("r_respondent between", value1, value2, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRRespondentNotBetween(String value1, String value2) {
            addCriterion("r_respondent not between", value1, value2, "rRespondent");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlIsNull() {
            addCriterion("r_avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlIsNotNull() {
            addCriterion("r_avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlEqualTo(String value) {
            addCriterion("r_avatar_url =", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlNotEqualTo(String value) {
            addCriterion("r_avatar_url <>", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlGreaterThan(String value) {
            addCriterion("r_avatar_url >", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("r_avatar_url >=", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlLessThan(String value) {
            addCriterion("r_avatar_url <", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("r_avatar_url <=", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlLike(String value) {
            addCriterion("r_avatar_url like", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlNotLike(String value) {
            addCriterion("r_avatar_url not like", value, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlIn(List<String> values) {
            addCriterion("r_avatar_url in", values, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlNotIn(List<String> values) {
            addCriterion("r_avatar_url not in", values, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlBetween(String value1, String value2) {
            addCriterion("r_avatar_url between", value1, value2, "rAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andRAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("r_avatar_url not between", value1, value2, "rAvatarUrl");
            return (Criteria) this;
        }
    }

    /**
     *
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