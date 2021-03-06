package com.fi.crm.campaigns.common.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DocumentType")
public class DocumentTypeDTO implements BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8288520071230783654L;
	private String id;
	private String name;

	
	public DocumentTypeDTO(String id) {
		super();
		this.id = id;
	}

	public DocumentTypeDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DocumentTypeDTO other = (DocumentTypeDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
