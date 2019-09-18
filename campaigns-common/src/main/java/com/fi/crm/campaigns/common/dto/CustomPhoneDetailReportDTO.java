package com.fi.crm.campaigns.common.dto;

import com.google.gson.Gson;

public class CustomPhoneDetailReportDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4075998570438964926L;
	private Integer campaniaId;
	private String telefono;
	private String celular;
	private String nombre;
	private String estado;
	private String observaciones;
	
	public Integer getCampaniaId() {
		return campaniaId;
	}
	public void setCampaniaId(Integer campaniaId) {
		this.campaniaId = campaniaId;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public String toString (){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
