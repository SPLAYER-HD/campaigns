package com.fi.crm.campaigns.common.enums;

public enum AuditEventTypeEnum {
	
	CREATE_CAMPAIGN ("CREATE_CAMPAIGN","AuditEventTypeEnum.CREATE_CAMPAIGN"),
	GET_CAMPAIGNS ("GET_CAMPAIGNS","AuditEventTypeEnum.GET_CAMPAIGNS"),
	UPDATE_CAMPAIGN ("UPDATE_CAMPAIGN","AuditEventTypeEnum.UPDATE_CAMPAIGN"),
	END_CAMPAIGN ("END_CAMPAIGN","AuditEventTypeEnum.END_CAMPAIGN"),
	CLOSE_CAMPAIGN ("CLOSE_CAMPAIGN","AuditEventTypeEnum.CLOSE_CAMPAIGN"),
	DELETE_CAMPAIGN ("DELETE_CAMPAIGN","AuditEventTypeEnum.DELETE_CAMPAIGN"),
	CREATE_AUTHOR ("CREATE_AUTHOR","AuditEventTypeEnum.CREATE_AUTHOR"),
	GET_AUTHORS ("GET_AUTHORS","AuditEventTypeEnum.GET_AUTHORS"),
	UPDATE_AUTHOR ("UPDATE_AUTHOR","AuditEventTypeEnum.UPDATE_AUTHOR"),
	DELETE_AUTHOR ("DELETE_AUTHOR","AuditEventTypeEnum.DELETE_AUTHOR"),
	APPROVE_CAMPAIGN ("APPROVE_CAMPAIGN","AuditEventTypeEnum.APPROVE_CAMPAIGN"),
	CONTACT_CLIENT ("CONTACT_CLIENT","AuditEventTypeEnum.CONTACT_CLIENT"),
	CREATE_TRACING ("CREATE_TRACING","AuditEventTypeEnum.CREATE_TRACING"),
	CREATE_TRACING_MESSAGE ("CREATE_TRACING_MESSAGE","AuditEventTypeEnum.CREATE_TRACING_MESSAGE"),
	UPDATE_TRACING ("UPDATE_TRACING","AuditEventTypeEnum.UPDATE_TRACING"),
	UPDATE_TRACING_MESSAGE ("UPDATE_TRACING_MESSAGE","AuditEventTypeEnum.UPDATE_TRACING_MESSAGE"),
	GET_TRACING ("GET_TRACING","AuditEventTypeEnum.GET_TRACING"),
	GET_TRACING_BY_CAMPAIGN_ID ("GET_TRACING_BY_CAMPAIGN_ID","AuditEventTypeEnum.GET_TRACING_BY_CAMPAIGN_ID"),
	GET_TRACING_MESSAGE_BY_CAMPAIGN_ID ("GET_TRACING_MESSAGE_BY_CAMPAIGN_ID","AuditEventTypeEnum.GET_TRACING_MESSAGE_BY_CAMPAIGN_ID"),
	;

	private String value;
	private String description;

	private AuditEventTypeEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}
	
	public String getDescription() {
		return description;
	}

	public static AuditEventTypeEnum byValue (String value){
		AuditEventTypeEnum[] values = values();
		for (AuditEventTypeEnum documentType : values)
			if (documentType.getValue().equals(value))
				return documentType;
		return null;
	}
}
