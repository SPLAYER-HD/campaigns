package com.fi.crm.campaigns.common.enums;

public enum ConstantsIdentifierEnum {

	NUMBERS_MONTHS_HISTORY ("NUMBERS_MONTHS_HISTORY","ConstantsIdentifierEnum.NUMBERS_MONTHS_HISTORY", "12"),
	HOURS_AFTER_LAST_STATUS ("HOURS_AFTER_LAST_STATUS","ConstantsIdentifierEnum.HOURS_AFTER_LAST_STATUS", "24"),
	DEFAULT_COMPANY ("DEFAULT_COMPANY","ConstantsIdentifierEnum.DEFAULT_COMPANY", "20"),
	DEFAULT_STORE ("DEFAULT_STORE","ConstantsIdentifierEnum.DEFAULT_STORE", "IM"),
	DEFAULT_COUNTRY ("DEFAULT_COUNTRY","ConstantsIdentifierEnum.DEFAULT_COUNTRY", "169"),
	FREE_CALLS_CUSTOMER_CRON_EXPR("FREE_CALLS_CUSTOMER_CRON_EXPR","ConstantsIdentifierEnum.FREE_CALLS_CUSTOMER_CRON_EXPR",""),
	DEFAULT_EMAIL_SENDER ("DEFAULT_EMAIL_SENDER","ConstantsIdentifierEnum.DEFAULT_EMAIL_SENDER", ""),
	MAIL_SERVER_IP ("MAIL_SERVER_IP","ConstantsIdentifierEnum.MAIL_SERVER_IP", ""),
	MAIL_SERVER_PORT ("MAIL_SERVER_PORT","ConstantsIdentifierEnum.MAIL_SERVER_PORT", ""),
	EXECUTE_AUTOMATIC_CAMPAIGNS_CRON_EXPR ("EXECUTE_AUTOMATIC_CAMPAIGNS_CRON_EXPR","ConstantsIdentifierEnum.EXECUTE_AUTOMATIC_CAMPAIGNS_CRON_EXPR", ""),
	END_CAMPAIGNS_AND_EVENTS_CRON_EXPR ("END_CAMPAIGNS_AND_EVENTS_CRON_EXPR","ConstantsIdentifierEnum.END_CAMPAIGNS_AND_EVENTS_CRON_EXPR", ""),
	SMS_APP_KEY("SMS_APP_KEY","ConstantsIdentifierEnum.SMS_APP_KEY", ""),
	SMS_APP_SECRET("SMS_APP_SECRET","ConstantsIdentifierEnum.SMS_APP_SECRET", ""),
	PHONE_INDICATIVE("PHONE_INDICATIVE","ConstantsIdentifierEnum.PHONE_INDICATIVE", ""),
	SMS_URL("SMS_URL","ConstantsIdentifierEnum.SMS_URL", ""),
	MAIL_SERVLET_URL("MAIL_SERVLET_URL", "ConstantsIdentifierEnum.MAIL_SERVLET_URL", ""),
	MAIL_TEMPLATE_PATH("MAIL_TEMPLATE_PATH", "ConstantsIdentifierEnum.MAIL_TEMPLATE_PATH", ""),
	ACTIVE_MQ_SERVER_URL("ACTIVE_MQ_SERVER_URL", "ConstantsIdentifierEnum.ACTIVE_MQ_SERVER_URL", ""),
	TWILIO_APPLICATION_SID("TWILIO_APPLICATION_SID", "ConstantsIdentifierEnum.TWILIO_APPLICATION_SID", ""),
	TWILIO_ACCOUNT_SID("TWILIO_ACCOUNT_SID", "ConstantsIdentifierEnum.TWILIO_ACCOUNT_SID", ""),
	TWILIO_AUTH_TOKEN("TWILIO_AUTH_TOKEN", "ConstantsIdentifierEnum.TWILIO_AUTH_TOKEN", ""),
	SMS_ITCLOUD_USER("SMS_ITCLOUD_USER", "ConstantsIdentifierEnum.SMS_ITCLOUD_USER", ""),
	SMS_ITCLOUD_PASSWORD("SMS_ITCLOUD_PASSWORD", "ConstantsIdentifierEnum.SMS_ITCLOUD_PASSWORD", ""),
	SMS_INDICATIVE("SMS_INDICATIVE", "ConstantsIdentifierEnum.SMS_INDICATIVE", ""),
	DEFAULT_ALL_CODE ("DEFAULT_ALL_CODE","ConstantsIdentifierEnum.DEFAULT_ALL_CODE","T"),
	QTY_MAILS_TO_SEND ("QTY_MAILS_TO_SEND","ConstantsIdentifierEnum.QTY_MAILS_TO_SEND", "1500"),
	START_SMS_HOUR ("START_SMS_HOUR","ConstantsIdentifierEnum.START_SMS_HOUR", "800"),
	END_SMS_HOUR ("END_SMS_HOUR","ConstantsIdentifierEnum.END_SMS_HOUR", "2100"),
	JAMES_AVIALABLE ("JAMES_AVIALABLE","ConstantsIdentifierEnum.JAMES_AVIALABLE", ""),
	;

	private String value;
	private String viewKey;
	private String defaultValue;

	private ConstantsIdentifierEnum(String value, String viewKey, String defaultValue) {
		this.value = value;
		this.viewKey = viewKey;
		this.defaultValue = defaultValue;
	}

	/**
	 * @param value
	 * @return
	 */
	public static ConstantsIdentifierEnum byValue ( String value ) {
		ConstantsIdentifierEnum[] values = ConstantsIdentifierEnum.values();
		for (int i = 0; i < values.length; i++) {
			if( values[i].value.equals( value ) )
				return values[i];
		}
		return null;
	}

	public String getValue (){
		return this.value;
	}

	public String getViewKey() {
		return viewKey;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
