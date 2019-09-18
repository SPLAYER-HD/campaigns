/**
 * 
 */
package com.fi.crm.campaigns.common.dto;

/**
 * @author JoseAugusto
 *
 */
public class BusinessUnitDTO implements BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String noCia;
	private String code;
    private String name;
	
    public BusinessUnitDTO() {
	}
    
	public BusinessUnitDTO(String noCia, String code) {
		super();
		this.noCia = noCia;
		this.code = code;
	}

	public String getNoCia() {
		return noCia;
	}
	public void setNoCia(String noCia) {
		this.noCia = noCia;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((noCia == null) ? 0 : noCia.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessUnitDTO other = (BusinessUnitDTO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (noCia == null) {
			if (other.noCia != null)
				return false;
		} else if (!noCia.equals(other.noCia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusinessUnitDTO [noCia=" + noCia + ", code=" + code + ", name=" + name + "]";
	}
	
	

}