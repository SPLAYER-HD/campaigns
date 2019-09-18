package com.fi.crm.campaigns.persistence.model;

public class CustomMessageClicksReport {
	private Integer tiempo;
	private Integer cantidad;
	private Float absoluto;
	private Float relativo;
	public Integer getTiempo() {
		return tiempo;
	}
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Float getAbsoluto() {
		return absoluto;
	}
	public void setAbsoluto(Float absoluto) {
		this.absoluto = absoluto;
	}
	public Float getRelativo() {
		return relativo;
	}
	public void setRelativo(Float relativo) {
		this.relativo = relativo;
	}
}
