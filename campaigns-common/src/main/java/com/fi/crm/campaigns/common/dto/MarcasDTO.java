package com.fi.crm.campaigns.common.dto;

import java.util.Date;

public class MarcasDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847151375347333335L;

	private String noCia;
	private String codigo;
	private String descripcion;
	private Date tstamp;
	private String indDemanda;
	public MarcasDTO () {
		
	}
	public MarcasDTO (String noCia, String codigo){
		this.noCia = noCia;
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getTstamp() {
		return tstamp;
	}
	public void setTstamp(Date tstamp) {
		this.tstamp = tstamp;
	}
	public String getIndDemanda() {
		return indDemanda;
	}
	public void setIndDemanda(String indDemanda) {
		this.indDemanda = indDemanda;
	}
	
	public String getNoCia() {
		return noCia;
	}
	public void setNoCia(String noCia) {
		this.noCia = noCia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString (){
		String result = "Marca [codigo="+codigo+ ","+"noCia="+noCia+
				",descripcion="+descripcion+",indDemanda="+indDemanda+
				",tstamp="+tstamp+"]";
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((noCia == null) ? 0 : noCia.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarcasDTO other = (MarcasDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (noCia == null) {
			if (other.noCia != null)
				return false;
		} else if (!noCia.equals(other.noCia))
			return false;
		return true;
	}

}
