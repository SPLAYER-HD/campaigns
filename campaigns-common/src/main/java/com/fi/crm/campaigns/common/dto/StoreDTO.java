package com.fi.crm.campaigns.common.dto;

public class StoreDTO implements BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String noCia;
    private String center;
    private String name;
	    
    public StoreDTO() {
	}
    
	public StoreDTO(String noCia, String center) {
		super();
		this.noCia = noCia;
		this.center = center;
	}

	public String getNoCia() {
		return noCia;
	}
	public void setNoCia(String noCia) {
		this.noCia = noCia;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
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
		result = prime * result + ((center == null) ? 0 : center.hashCode());
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
		StoreDTO other = (StoreDTO) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (noCia == null) {
			if (other.noCia != null)
				return false;
		} else if (!noCia.equals(other.noCia))
			return false;
		return true;
	}

	
}
