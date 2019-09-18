package com.fi.crm.campaigns.persistence.model;

import java.util.Date;


public class CustomCpSeguimientoMensaje{
	
	private Integer segMensajeId;
    private Integer clienteId;
    private Integer campaniaId;
    private String estado;
    private Date fechaUltEstad;
	
	private String tipoIdentificacion;
    private String identificacion;
    private String nombres;
    private String apellido1;
    private String apellido2;
    private String indicativo;
    private String telefono;
    private String celular;
    private String genero;
    private String correo;
    private String extraStr;
    private Integer extraNum;
    private Date extraFecha;
	public Integer getSegMensajeId() {
		return segMensajeId;
	}
	public void setSegMensajeId(Integer segMensajeId) {
		this.segMensajeId = segMensajeId;
	}
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public Integer getCampaniaId() {
		return campaniaId;
	}
	public void setCampaniaId(Integer campaniaId) {
		this.campaniaId = campaniaId;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaUltEstad() {
		return fechaUltEstad;
	}
	public void setFechaUltEstad(Date fechaUltEstad) {
		this.fechaUltEstad = fechaUltEstad;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getIndicativo() {
		return indicativo;
	}
	public void setIndicativo(String indicativo) {
		this.indicativo = indicativo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getExtraStr() {
		return extraStr;
	}
	public void setExtraStr(String extraStr) {
		this.extraStr = extraStr;
	}
	public Integer getExtraNum() {
		return extraNum;
	}
	public void setExtraNum(Integer extraNum) {
		this.extraNum = extraNum;
	}
	public Date getExtraFecha() {
		return extraFecha;
	}
	public void setExtraFecha(Date extraFecha) {
		this.extraFecha = extraFecha;
	}

    
}