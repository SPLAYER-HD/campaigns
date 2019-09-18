package com.fi.crm.campaigns.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class CpEstadoSeguimientoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public CpEstadoSeguimientoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
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
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
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
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
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
     * This class corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
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

        public Criteria andEstadoIdIsNull() {
            addCriterion("ESTADO_ID is null");
            return (Criteria) this;
        }

        public Criteria andEstadoIdIsNotNull() {
            addCriterion("ESTADO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEstadoIdEqualTo(Integer value) {
            addCriterion("ESTADO_ID =", value, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdNotEqualTo(Integer value) {
            addCriterion("ESTADO_ID <>", value, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdGreaterThan(Integer value) {
            addCriterion("ESTADO_ID >", value, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ESTADO_ID >=", value, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdLessThan(Integer value) {
            addCriterion("ESTADO_ID <", value, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdLessThanOrEqualTo(Integer value) {
            addCriterion("ESTADO_ID <=", value, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdIn(List<Integer> values) {
            addCriterion("ESTADO_ID in", values, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdNotIn(List<Integer> values) {
            addCriterion("ESTADO_ID not in", values, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdBetween(Integer value1, Integer value2) {
            addCriterion("ESTADO_ID between", value1, value2, "estadoId");
            return (Criteria) this;
        }

        public Criteria andEstadoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ESTADO_ID not between", value1, value2, "estadoId");
            return (Criteria) this;
        }

        public Criteria andNombreIsNull() {
            addCriterion("NOMBRE is null");
            return (Criteria) this;
        }

        public Criteria andNombreIsNotNull() {
            addCriterion("NOMBRE is not null");
            return (Criteria) this;
        }

        public Criteria andNombreEqualTo(String value) {
            addCriterion("NOMBRE =", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreNotEqualTo(String value) {
            addCriterion("NOMBRE <>", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreGreaterThan(String value) {
            addCriterion("NOMBRE >", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreGreaterThanOrEqualTo(String value) {
            addCriterion("NOMBRE >=", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreLessThan(String value) {
            addCriterion("NOMBRE <", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreLessThanOrEqualTo(String value) {
            addCriterion("NOMBRE <=", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreLike(String value) {
            addCriterion("NOMBRE like", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreNotLike(String value) {
            addCriterion("NOMBRE not like", value, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreIn(List<String> values) {
            addCriterion("NOMBRE in", values, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreNotIn(List<String> values) {
            addCriterion("NOMBRE not in", values, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreBetween(String value1, String value2) {
            addCriterion("NOMBRE between", value1, value2, "nombre");
            return (Criteria) this;
        }

        public Criteria andNombreNotBetween(String value1, String value2) {
            addCriterion("NOMBRE not between", value1, value2, "nombre");
            return (Criteria) this;
        }

        public Criteria andDescripcionIsNull() {
            addCriterion("DESCRIPCION is null");
            return (Criteria) this;
        }

        public Criteria andDescripcionIsNotNull() {
            addCriterion("DESCRIPCION is not null");
            return (Criteria) this;
        }

        public Criteria andDescripcionEqualTo(String value) {
            addCriterion("DESCRIPCION =", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionNotEqualTo(String value) {
            addCriterion("DESCRIPCION <>", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionGreaterThan(String value) {
            addCriterion("DESCRIPCION >", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPCION >=", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionLessThan(String value) {
            addCriterion("DESCRIPCION <", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPCION <=", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionLike(String value) {
            addCriterion("DESCRIPCION like", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionNotLike(String value) {
            addCriterion("DESCRIPCION not like", value, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionIn(List<String> values) {
            addCriterion("DESCRIPCION in", values, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionNotIn(List<String> values) {
            addCriterion("DESCRIPCION not in", values, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionBetween(String value1, String value2) {
            addCriterion("DESCRIPCION between", value1, value2, "descripcion");
            return (Criteria) this;
        }

        public Criteria andDescripcionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPCION not between", value1, value2, "descripcion");
            return (Criteria) this;
        }

        public Criteria andReintentarIsNull() {
            addCriterion("REINTENTAR is null");
            return (Criteria) this;
        }

        public Criteria andReintentarIsNotNull() {
            addCriterion("REINTENTAR is not null");
            return (Criteria) this;
        }

        public Criteria andReintentarEqualTo(String value) {
            addCriterion("REINTENTAR =", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarNotEqualTo(String value) {
            addCriterion("REINTENTAR <>", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarGreaterThan(String value) {
            addCriterion("REINTENTAR >", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarGreaterThanOrEqualTo(String value) {
            addCriterion("REINTENTAR >=", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarLessThan(String value) {
            addCriterion("REINTENTAR <", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarLessThanOrEqualTo(String value) {
            addCriterion("REINTENTAR <=", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarLike(String value) {
            addCriterion("REINTENTAR like", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarNotLike(String value) {
            addCriterion("REINTENTAR not like", value, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarIn(List<String> values) {
            addCriterion("REINTENTAR in", values, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarNotIn(List<String> values) {
            addCriterion("REINTENTAR not in", values, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarBetween(String value1, String value2) {
            addCriterion("REINTENTAR between", value1, value2, "reintentar");
            return (Criteria) this;
        }

        public Criteria andReintentarNotBetween(String value1, String value2) {
            addCriterion("REINTENTAR not between", value1, value2, "reintentar");
            return (Criteria) this;
        }

        public Criteria andTipoIsNull() {
            addCriterion("TIPO is null");
            return (Criteria) this;
        }

        public Criteria andTipoIsNotNull() {
            addCriterion("TIPO is not null");
            return (Criteria) this;
        }

        public Criteria andTipoEqualTo(String value) {
            addCriterion("TIPO =", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoNotEqualTo(String value) {
            addCriterion("TIPO <>", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoGreaterThan(String value) {
            addCriterion("TIPO >", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoGreaterThanOrEqualTo(String value) {
            addCriterion("TIPO >=", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoLessThan(String value) {
            addCriterion("TIPO <", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoLessThanOrEqualTo(String value) {
            addCriterion("TIPO <=", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoLike(String value) {
            addCriterion("TIPO like", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoNotLike(String value) {
            addCriterion("TIPO not like", value, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoIn(List<String> values) {
            addCriterion("TIPO in", values, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoNotIn(List<String> values) {
            addCriterion("TIPO not in", values, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoBetween(String value1, String value2) {
            addCriterion("TIPO between", value1, value2, "tipo");
            return (Criteria) this;
        }

        public Criteria andTipoNotBetween(String value1, String value2) {
            addCriterion("TIPO not between", value1, value2, "tipo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
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
     * This class corresponds to the database table CAMPAIGNS.CP_ESTADO_SEGUIMIENTO
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