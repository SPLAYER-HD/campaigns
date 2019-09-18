package com.fi.crm.campaigns.common.enums;

public enum OperationStatusEnum {
	OK("OK"),
	WARNING("WARNING"),
	ERROR("ERROR")
	;

	private String value;

	private OperationStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
	
	public static OperationStatusEnum byValue (String value){
		OperationStatusEnum[] values = values();
		for (OperationStatusEnum sequenceType : values)
			if (sequenceType.getValue().equals(value))
				return sequenceType;
		return null;
	}
}
