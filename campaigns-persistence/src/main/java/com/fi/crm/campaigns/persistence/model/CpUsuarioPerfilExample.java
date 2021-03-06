package com.fi.crm.campaigns.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class CpUsuarioPerfilExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public CpUsuarioPerfilExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
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

        public Criteria andUsuPerIdIsNull() {
            addCriterion("USU_PER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdIsNotNull() {
            addCriterion("USU_PER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdEqualTo(Integer value) {
            addCriterion("USU_PER_ID =", value, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdNotEqualTo(Integer value) {
            addCriterion("USU_PER_ID <>", value, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdGreaterThan(Integer value) {
            addCriterion("USU_PER_ID >", value, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USU_PER_ID >=", value, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdLessThan(Integer value) {
            addCriterion("USU_PER_ID <", value, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdLessThanOrEqualTo(Integer value) {
            addCriterion("USU_PER_ID <=", value, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdIn(List<Integer> values) {
            addCriterion("USU_PER_ID in", values, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdNotIn(List<Integer> values) {
            addCriterion("USU_PER_ID not in", values, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdBetween(Integer value1, Integer value2) {
            addCriterion("USU_PER_ID between", value1, value2, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andUsuPerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USU_PER_ID not between", value1, value2, "usuPerId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdIsNull() {
            addCriterion("PERFIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andPerfilIdIsNotNull() {
            addCriterion("PERFIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPerfilIdEqualTo(Integer value) {
            addCriterion("PERFIL_ID =", value, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdNotEqualTo(Integer value) {
            addCriterion("PERFIL_ID <>", value, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdGreaterThan(Integer value) {
            addCriterion("PERFIL_ID >", value, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PERFIL_ID >=", value, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdLessThan(Integer value) {
            addCriterion("PERFIL_ID <", value, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdLessThanOrEqualTo(Integer value) {
            addCriterion("PERFIL_ID <=", value, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdIn(List<Integer> values) {
            addCriterion("PERFIL_ID in", values, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdNotIn(List<Integer> values) {
            addCriterion("PERFIL_ID not in", values, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdBetween(Integer value1, Integer value2) {
            addCriterion("PERFIL_ID between", value1, value2, "perfilId");
            return (Criteria) this;
        }

        public Criteria andPerfilIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PERFIL_ID not between", value1, value2, "perfilId");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioIsNull() {
            addCriterion("COD_USUARIO is null");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioIsNotNull() {
            addCriterion("COD_USUARIO is not null");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioEqualTo(String value) {
            addCriterion("COD_USUARIO =", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioNotEqualTo(String value) {
            addCriterion("COD_USUARIO <>", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioGreaterThan(String value) {
            addCriterion("COD_USUARIO >", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioGreaterThanOrEqualTo(String value) {
            addCriterion("COD_USUARIO >=", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioLessThan(String value) {
            addCriterion("COD_USUARIO <", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioLessThanOrEqualTo(String value) {
            addCriterion("COD_USUARIO <=", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioLike(String value) {
            addCriterion("COD_USUARIO like", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioNotLike(String value) {
            addCriterion("COD_USUARIO not like", value, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioIn(List<String> values) {
            addCriterion("COD_USUARIO in", values, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioNotIn(List<String> values) {
            addCriterion("COD_USUARIO not in", values, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioBetween(String value1, String value2) {
            addCriterion("COD_USUARIO between", value1, value2, "codUsuario");
            return (Criteria) this;
        }

        public Criteria andCodUsuarioNotBetween(String value1, String value2) {
            addCriterion("COD_USUARIO not between", value1, value2, "codUsuario");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated do_not_delete_during_merge Tue Apr 21 12:41:59 COT 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CAMPAIGNS.CP_USUARIO_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
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