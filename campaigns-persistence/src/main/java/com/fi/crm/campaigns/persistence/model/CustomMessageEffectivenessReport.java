package com.fi.crm.campaigns.persistence.model;

import java.math.BigDecimal;

public class CustomMessageEffectivenessReport {
	private Integer unidadesCliente;
	private BigDecimal ventasCliente;
	private Integer unidadesTotales;
	private BigDecimal ventasTotales;
	private BigDecimal porcentajeUnidades;
	private BigDecimal porcentajeVentas;
	
	public CustomMessageEffectivenessReport() {
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
