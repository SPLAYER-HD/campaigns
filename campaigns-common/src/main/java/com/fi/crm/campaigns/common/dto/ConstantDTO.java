package com.fi.crm.campaigns.common.dto;

public class ConstantDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String constantId;
	private String description;
	private String value;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConstantId() {
		return constantId;
	}

	public void setConstantId(String constantId) {
		this.constantId = constantId;
	}
}
