package com.fi.crm.campaigns.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarcasExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public MarcasExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
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
     * This method corresponds to the database table NAF6.MARCAS
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
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
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
     * This class corresponds to the database table NAF6.MARCAS
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

        public Criteria andNoCiaIsNull() {
            addCriterion("NO_CIA is null");
            return (Criteria) this;
        }

        public Criteria andNoCiaIsNotNull() {
            addCriterion("NO_CIA is not null");
            return (Criteria) this;
        }

        public Criteria andNoCiaEqualTo(String value) {
            addCriterion("NO_CIA =", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaNotEqualTo(String value) {
            addCriterion("NO_CIA <>", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaGreaterThan(String value) {
            addCriterion("NO_CIA >", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaGreaterThanOrEqualTo(String value) {
            addCriterion("NO_CIA >=", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaLessThan(String value) {
            addCriterion("NO_CIA <", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaLessThanOrEqualTo(String value) {
            addCriterion("NO_CIA <=", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaLike(String value) {
            addCriterion("NO_CIA like", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaNotLike(String value) {
            addCriterion("NO_CIA not like", value, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaIn(List<String> values) {
            addCriterion("NO_CIA in", values, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaNotIn(List<String> values) {
            addCriterion("NO_CIA not in", values, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaBetween(String value1, String value2) {
            addCriterion("NO_CIA between", value1, value2, "noCia");
            return (Criteria) this;
        }

        public Criteria andNoCiaNotBetween(String value1, String value2) {
            addCriterion("NO_CIA not between", value1, value2, "noCia");
            return (Criteria) this;
        }

        public Criteria andCodigoIsNull() {
            addCriterion("CODIGO is null");
            return (Criteria) this;
        }

        public Criteria andCodigoIsNotNull() {
            addCriterion("CODIGO is not null");
            return (Criteria) this;
        }

        public Criteria andCodigoEqualTo(String value) {
            addCriterion("CODIGO =", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoNotEqualTo(String value) {
            addCriterion("CODIGO <>", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoGreaterThan(String value) {
            addCriterion("CODIGO >", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoGreaterThanOrEqualTo(String value) {
            addCriterion("CODIGO >=", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoLessThan(String value) {
            addCriterion("CODIGO <", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoLessThanOrEqualTo(String value) {
            addCriterion("CODIGO <=", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoLike(String value) {
            addCriterion("CODIGO like", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoNotLike(String value) {
            addCriterion("CODIGO not like", value, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoIn(List<String> values) {
            addCriterion("CODIGO in", values, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoNotIn(List<String> values) {
            addCriterion("CODIGO not in", values, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoBetween(String value1, String value2) {
            addCriterion("CODIGO between", value1, value2, "codigo");
            return (Criteria) this;
        }

        public Criteria andCodigoNotBetween(String value1, String value2) {
            addCriterion("CODIGO not between", value1, value2, "codigo");
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

        public Criteria andTstampIsNull() {
            addCriterion("TSTAMP is null");
            return (Criteria) this;
        }

        public Criteria andTstampIsNotNull() {
            addCriterion("TSTAMP is not null");
            return (Criteria) this;
        }

        public Criteria andTstampEqualTo(Date value) {
            addCriterion("TSTAMP =", value, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampNotEqualTo(Date value) {
            addCriterion("TSTAMP <>", value, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampGreaterThan(Date value) {
            addCriterion("TSTAMP >", value, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampGreaterThanOrEqualTo(Date value) {
            addCriterion("TSTAMP >=", value, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampLessThan(Date value) {
            addCriterion("TSTAMP <", value, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampLessThanOrEqualTo(Date value) {
            addCriterion("TSTAMP <=", value, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampIn(List<Date> values) {
            addCriterion("TSTAMP in", values, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampNotIn(List<Date> values) {
            addCriterion("TSTAMP not in", values, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampBetween(Date value1, Date value2) {
            addCriterion("TSTAMP between", value1, value2, "tstamp");
            return (Criteria) this;
        }

        public Criteria andTstampNotBetween(Date value1, Date value2) {
            addCriterion("TSTAMP not between", value1, value2, "tstamp");
            return (Criteria) this;
        }

        public Criteria andIndDemandaIsNull() {
            addCriterion("IND_DEMANDA is null");
            return (Criteria) this;
        }

        public Criteria andIndDemandaIsNotNull() {
            addCriterion("IND_DEMANDA is not null");
            return (Criteria) this;
        }

        public Criteria andIndDemandaEqualTo(String value) {
            addCriterion("IND_DEMANDA =", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaNotEqualTo(String value) {
            addCriterion("IND_DEMANDA <>", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaGreaterThan(String value) {
            addCriterion("IND_DEMANDA >", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaGreaterThanOrEqualTo(String value) {
            addCriterion("IND_DEMANDA >=", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaLessThan(String value) {
            addCriterion("IND_DEMANDA <", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaLessThanOrEqualTo(String value) {
            addCriterion("IND_DEMANDA <=", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaLike(String value) {
            addCriterion("IND_DEMANDA like", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaNotLike(String value) {
            addCriterion("IND_DEMANDA not like", value, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaIn(List<String> values) {
            addCriterion("IND_DEMANDA in", values, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaNotIn(List<String> values) {
            addCriterion("IND_DEMANDA not in", values, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaBetween(String value1, String value2) {
            addCriterion("IND_DEMANDA between", value1, value2, "indDemanda");
            return (Criteria) this;
        }

        public Criteria andIndDemandaNotBetween(String value1, String value2) {
            addCriterion("IND_DEMANDA not between", value1, value2, "indDemanda");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table NAF6.MARCAS
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
     * This class corresponds to the database table NAF6.MARCAS
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