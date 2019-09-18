package com.fi.crm.campaigns.persistence.model;

public class CustomPhoneHeaderReportEffectiveCalls {
	private Integer campaniaId;
	private String nombre;
	private Integer cantidad;
	private Float porcentaje;
	
	public Integer getCampaniaId() {
		return campaniaId;
	}
	public void setCampaniaId(Integer campaniaId) {
		this.campaniaId = campaniaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
