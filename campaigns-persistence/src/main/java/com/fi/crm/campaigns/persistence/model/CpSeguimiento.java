package com.fi.crm.campaigns.persistence.model;

import java.util.Date;

public class CpSeguimiento {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.SEGUIMIENTO_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private Integer seguimientoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.CLIENTE_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private Integer clienteId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.CAMPANIA_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private Integer campaniaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.ESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private String estado;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.SUBESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private Integer subestado;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.FECHA_ULT_ESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private Date fechaUltEstado;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.USUARIO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private String usuario;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.OBSERVACIONES
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private String observaciones;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.DURACION
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private Integer duracion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMPAIGNS.CP_SEGUIMIENTO.LLAMANDO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    private String llamando;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.SEGUIMIENTO_ID
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.SEGUIMIENTO_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Integer getSeguimientoId() {
        return seguimientoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.SEGUIMIENTO_ID
     *
     * @param seguimientoId the value for CAMPAIGNS.CP_SEGUIMIENTO.SEGUIMIENTO_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setSeguimientoId(Integer seguimientoId) {
        this.seguimientoId = seguimientoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.CLIENTE_ID
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.CLIENTE_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Integer getClienteId() {
        return clienteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.CLIENTE_ID
     *
     * @param clienteId the value for CAMPAIGNS.CP_SEGUIMIENTO.CLIENTE_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.CAMPANIA_ID
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.CAMPANIA_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Integer getCampaniaId() {
        return campaniaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.CAMPANIA_ID
     *
     * @param campaniaId the value for CAMPAIGNS.CP_SEGUIMIENTO.CAMPANIA_ID
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setCampaniaId(Integer campaniaId) {
        this.campaniaId = campaniaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.ESTADO
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.ESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getEstado() {
        return estado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.ESTADO
     *
     * @param estado the value for CAMPAIGNS.CP_SEGUIMIENTO.ESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.SUBESTADO
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.SUBESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Integer getSubestado() {
        return subestado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.SUBESTADO
     *
     * @param subestado the value for CAMPAIGNS.CP_SEGUIMIENTO.SUBESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setSubestado(Integer subestado) {
        this.subestado = subestado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.FECHA_ULT_ESTADO
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.FECHA_ULT_ESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Date getFechaUltEstado() {
        return fechaUltEstado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.FECHA_ULT_ESTADO
     *
     * @param fechaUltEstado the value for CAMPAIGNS.CP_SEGUIMIENTO.FECHA_ULT_ESTADO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setFechaUltEstado(Date fechaUltEstado) {
        this.fechaUltEstado = fechaUltEstado;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.USUARIO
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.USUARIO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.USUARIO
     *
     * @param usuario the value for CAMPAIGNS.CP_SEGUIMIENTO.USUARIO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.OBSERVACIONES
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.OBSERVACIONES
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.OBSERVACIONES
     *
     * @param observaciones the value for CAMPAIGNS.CP_SEGUIMIENTO.OBSERVACIONES
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.DURACION
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.DURACION
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.DURACION
     *
     * @param duracion the value for CAMPAIGNS.CP_SEGUIMIENTO.DURACION
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.LLAMANDO
     *
     * @return the value of CAMPAIGNS.CP_SEGUIMIENTO.LLAMANDO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public String getLlamando() {
        return llamando;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMPAIGNS.CP_SEGUIMIENTO.LLAMANDO
     *
     * @param llamando the value for CAMPAIGNS.CP_SEGUIMIENTO.LLAMANDO
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    public void setLlamando(String llamando) {
        this.llamando = llamando;
    }
}