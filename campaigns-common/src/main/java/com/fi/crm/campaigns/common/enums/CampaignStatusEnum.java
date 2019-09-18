package com.fi.crm.campaigns.common.enums;

public enum CampaignStatusEnum {

	CREATED("CREATED", "CampaignStatusEnum.created"), 
	APPROVED("APPROVED", "CampaignStatusEnum.approved"), 
	REJECTED("REJECTED", "CampaignStatusEnum.rejected"),
	FINISHED("FINISHED", "CampaignStatusEnum.finished"),
	CLOSED("CLOSED", "CampaignStatusEnum.closed"),
	ALL ("ALL", "CampaignStatusEnum.all");

	private String value;
	private String viewKey;

	private CampaignStatusEnum(String value, String viewKey) {
		this.value = value;
		this.viewKey = viewKey;
	}

	public static CampaignStatusEnum byValue(String value) {
		CampaignStatusEnum[] values = CampaignStatusEnum.values();
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
