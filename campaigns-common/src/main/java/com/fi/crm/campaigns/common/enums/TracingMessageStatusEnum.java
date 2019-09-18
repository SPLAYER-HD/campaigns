package com.fi.crm.campaigns.common.enums;

public enum TracingMessageStatusEnum {

	MSJ_SENT("MSJ_SENT", "TracingMessageStatusEnum.MSJ_SENT"), 
	MSJ_SEEN("MSJ_SEEN", "TracingMessageStatusEnum.MSJ_SEEN"),	
	MSJ_INVALID_EMAIL("MSJ_INVALID_EMAIL", "TracingMessageStatusEnum.MSJ_INVALID_EMAIL"),
	
	FAIL_AUTENTICATION("-1", "TracingMessageStatusEnum.FAIL_AUTENTICATION"),
	OUTSIDE_AUTHORIZED_HOURS("-2", "TracingMessageStatusEnum.OUTSIDE_AUTHORIZED_HOURS"),
	BALANCE_ERROR("-3", "TracingMessageStatusEnum.BALANCE_ERROR"),
	MOBILE_FORMAT_ERROR("-4", "TracingMessageStatusEnum.MOBILE_FORMAT_ERROR"),
	MESSAGE_ERROR("-5", "TracingMessageStatusEnum.MESSAGE_ERROR"),
	SYSTEM_IN_MAINTENANCE("-6", "TracingMessageStatusEnum.SYSTEM_IN_MAINTENANCE"),
	QUANTITY_MAX_MOBILE("-7", "TracingMessageStatusEnum.QUANTITY_MAX_MOBILE"),
	OPERATOR_NOT_EXISTS("0", "TracingMessageStatusEnum.OPERATOR_NOT_EXISTS"),
	;

	private String value;
	private String viewKey;

	private TracingMessageStatusEnum(String value, String viewKey) {
		this.value = value;
		this.viewKey = viewKey;
	}

	public static TracingMessageStatusEnum byValue(String value) {
		TracingMessageStatusEnum[] values = TracingMessageStatusEnum.values();
		for (int i = 0; i < values.length; i++) {
			if (values[i].value.equals(value))
				return values[i];
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public String getViewKey() {
		return viewKey;
	}
}