package com.fi.crm.campaigns.common.dto;

public class CustomPhoneHeaderReportEffectiveCallsDTO implements BaseDTO {

	private static final long serialVersionUID = 5119350203936516937L;
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
