package com.fi.crm.campaigns.persistence.model;

import java.util.Date;


public class CustomCpSeguimiento{
	
	private Integer seguimientoId;
    private Integer clienteId;
    private Integer campaniaId;
    private String estado;
    private Integer subestado;
    private Date fechaUltEstado;
    private String usuario;
    private String observaciones;
    private Integer duracion;
    private String llamando;

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
	public Integer getSeguimientoId() {
		return seguimientoId;
	}
	public void setSeguimientoId(Integer seguimientoId) {
		this.seguimientoId = seguimientoId;
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
	public Integer getSubestado() {
		return subestado;
	}
	public void setSubestado(Integer subestado) {
		this.subestado = subestado;
	}
	public Date getFechaUltEstado() {
		return fechaUltEstado;
	}
	public void setFechaUltEstado(Date fechaUltEstado) {
		this.fechaUltEstado = fechaUltEstado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public String getLlamando() {
		return llamando;
	}
	public void setLlamando(String llamando) {
		this.llamando = llamando;
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