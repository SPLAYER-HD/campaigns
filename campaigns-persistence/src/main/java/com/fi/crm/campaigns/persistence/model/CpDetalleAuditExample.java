package com.fi.crm.campaigns.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class CpDetalleAuditExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public CpDetalleAuditExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
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
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
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
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
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
     * This class corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
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

        public Criteria andDetalleIdIsNull() {
            addCriterion("DETALLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDetalleIdIsNotNull() {
            addCriterion("DETALLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDetalleIdEqualTo(Integer value) {
            addCriterion("DETALLE_ID =", value, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdNotEqualTo(Integer value) {
            addCriterion("DETALLE_ID <>", value, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdGreaterThan(Integer value) {
            addCriterion("DETALLE_ID >", value, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DETALLE_ID >=", value, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdLessThan(Integer value) {
            addCriterion("DETALLE_ID <", value, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdLessThanOrEqualTo(Integer value) {
            addCriterion("DETALLE_ID <=", value, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdIn(List<Integer> values) {
            addCriterion("DETALLE_ID in", values, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdNotIn(List<Integer> values) {
            addCriterion("DETALLE_ID not in", values, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdBetween(Integer value1, Integer value2) {
            addCriterion("DETALLE_ID between", value1, value2, "detalleId");
            return (Criteria) this;
        }

        public Criteria andDetalleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DETALLE_ID not between", value1, value2, "detalleId");
            return (Criteria) this;
        }

        public Criteria andAuditIdIsNull() {
            addCriterion("AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditIdIsNotNull() {
            addCriterion("AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditIdEqualTo(Integer value) {
            addCriterion("AUDIT_ID =", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotEqualTo(Integer value) {
            addCriterion("AUDIT_ID <>", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdGreaterThan(Integer value) {
            addCriterion("AUDIT_ID >", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_ID >=", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdLessThan(Integer value) {
            addCriterion("AUDIT_ID <", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdLessThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_ID <=", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdIn(List<Integer> values) {
            addCriterion("AUDIT_ID in", values, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotIn(List<Integer> values) {
            addCriterion("AUDIT_ID not in", values, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_ID between", value1, value2, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_ID not between", value1, value2, "auditId");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleIsNull() {
            addCriterion("LLAVE_DETALLE is null");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleIsNotNull() {
            addCriterion("LLAVE_DETALLE is not null");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleEqualTo(String value) {
            addCriterion("LLAVE_DETALLE =", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleNotEqualTo(String value) {
            addCriterion("LLAVE_DETALLE <>", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleGreaterThan(String value) {
            addCriterion("LLAVE_DETALLE >", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleGreaterThanOrEqualTo(String value) {
            addCriterion("LLAVE_DETALLE >=", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleLessThan(String value) {
            addCriterion("LLAVE_DETALLE <", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleLessThanOrEqualTo(String value) {
            addCriterion("LLAVE_DETALLE <=", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleLike(String value) {
            addCriterion("LLAVE_DETALLE like", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleNotLike(String value) {
            addCriterion("LLAVE_DETALLE not like", value, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleIn(List<String> values) {
            addCriterion("LLAVE_DETALLE in", values, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleNotIn(List<String> values) {
            addCriterion("LLAVE_DETALLE not in", values, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleBetween(String value1, String value2) {
            addCriterion("LLAVE_DETALLE between", value1, value2, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andLlaveDetalleNotBetween(String value1, String value2) {
            addCriterion("LLAVE_DETALLE not between", value1, value2, "llaveDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleIsNull() {
            addCriterion("VALOR_DETALLE is null");
            return (Criteria) this;
        }

        public Criteria andValorDetalleIsNotNull() {
            addCriterion("VALOR_DETALLE is not null");
            return (Criteria) this;
        }

        public Criteria andValorDetalleEqualTo(String value) {
            addCriterion("VALOR_DETALLE =", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleNotEqualTo(String value) {
            addCriterion("VALOR_DETALLE <>", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleGreaterThan(String value) {
            addCriterion("VALOR_DETALLE >", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleGreaterThanOrEqualTo(String value) {
            addCriterion("VALOR_DETALLE >=", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleLessThan(String value) {
            addCriterion("VALOR_DETALLE <", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleLessThanOrEqualTo(String value) {
            addCriterion("VALOR_DETALLE <=", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleLike(String value) {
            addCriterion("VALOR_DETALLE like", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleNotLike(String value) {
            addCriterion("VALOR_DETALLE not like", value, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleIn(List<String> values) {
            addCriterion("VALOR_DETALLE in", values, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleNotIn(List<String> values) {
            addCriterion("VALOR_DETALLE not in", values, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleBetween(String value1, String value2) {
            addCriterion("VALOR_DETALLE between", value1, value2, "valorDetalle");
            return (Criteria) this;
        }

        public Criteria andValorDetalleNotBetween(String value1, String value2) {
            addCriterion("VALOR_DETALLE not between", value1, value2, "valorDetalle");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
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
     * This class corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
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