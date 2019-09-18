package com.fi.crm.campaigns.common.enums;

public enum TracingStatusEnum {

	CONTACTED("CONTACTED", "TracingStatusEnum.CONTACTED"), 
	NOT_CONTACTED("NOT_CONTACTED", "TracingStatusEnum.NOT_CONTACTED"),
	;

	private String value;
	private String viewKey;

	private TracingStatusEnum(String value, String viewKey) {
		this.value = value;
		this.viewKey = viewKey;
	}

	public static TracingStatusEnum byValue(String value) {
		TracingStatusEnum[] values = TracingStatusEnum.values();
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