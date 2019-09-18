package com.fi.crm.campaigns.common.dto;

import java.math.BigDecimal;

public class CustomMessageEffectivenessReportDTO implements BaseDTO {
	private static final long serialVersionUID = 67763039232663212L;
	private Integer unidadesCliente;
	private BigDecimal ventasCliente;
	private Integer unidadesTotales;
	private BigDecimal ventasTotales;
	private BigDecimal porcentajeUnidades;
	private BigDecimal porcentajeVentas;
	
	public CustomMessageEffectivenessReportDTO() {
		super();
	}
	public Integer getUnidadesCliente() {
		return unidadesCliente;
	}
	public void setUnidadesCliente(Integer unidadesCliente) {
		this.unidadesCliente = unidadesCliente;
	}
	public BigDecimal getVentasCliente() {
		return ventasCliente;
	}
	public void setVentasCliente(BigDecimal ventasCliente) {
		this.ventasCliente = ventasCliente;
	}
	public Integer getUnidadesTotales() {
		return unidadesTotales;
	}
	public void setUnidadesTotales(Integer unidadesTotales) {
		this.unidadesTotales = unidadesTotales;
	}
	public BigDecimal getVentasTotales() {
		return ventasTotales;
	}
	public void setVentasTotales(BigDecimal ventasTotales) {
		this.ventasTotales = ventasTotales;
	}
	public BigDecimal getPorcentajeUnidades() {
		return porcentajeUnidades;
	}
	public void setPorcentajeUnidades(BigDecimal porcentajeUnidades) {
		this.porcentajeUnidades = porcentajeUnidades;
	}
	public BigDecimal getPorcentajeVentas() {
		return porcentajeVentas;
	}
	public void setPorcentajeVentas(BigDecimal porcentajeVentas) {
		this.porcentajeVentas = porcentajeVentas;
	}
}
