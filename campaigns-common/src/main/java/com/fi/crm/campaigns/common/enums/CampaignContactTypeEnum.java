package com.fi.crm.campaigns.common.enums;

public enum CampaignContactTypeEnum {

	PHONE("PHONE", "CampaignContactTypeEnum.phone"), 
	SMS("SMS", "CampaignContactTypeEnum.sms"), 
	EMAIL("EMAIL", "CampaignContactTypeEnum.email");

	private String value;
	private String viewKey;

	private CampaignContactTypeEnum(String value, String viewKey) {
		this.value = value;
		this.viewKey = viewKey;
	}

	public static CampaignContactTypeEnum byValue(String value) {
		CampaignContactTypeEnum[] values = CampaignContactTypeEnum.values();
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