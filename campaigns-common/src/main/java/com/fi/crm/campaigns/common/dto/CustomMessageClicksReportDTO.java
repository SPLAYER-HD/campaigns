package com.fi.crm.campaigns.common.dto;

public class CustomMessageClicksReportDTO implements BaseDTO {
	private static final long serialVersionUID = -9029694761822496380L;
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
