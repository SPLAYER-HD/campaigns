
package com.fi.crm.campaigns.common.enums;

public enum  PermissionEnum {

	//Permisos relacionados con la gestion de campañas
	CAMPAIGN_ADMIN_INSERT("PermissionEnum.CAMPAIGN_ADMIN_INSERT"),
	CAMPAIGN_ADMIN_UPDATE("PermissionEnum.CAMPAIGN_ADMIN_UPDATE"),
	CAMPAIGN_ADMIN_DELETE("PermissionEnum.CAMPAIGN_ADMIN_DELETE"),
	CAMPAIGN_ADMIN_APROVE("PermissionEnum.CAMPAIGN_ADMIN_APROVE"),
	CAMPAIGN_ADMIN_SEARCH("PermissionEnum.CAMPAIGN_ADMIN_SEARCH"),
	CAMPAIGN_ADMIN_VIEW("PermissionEnum.CAMPAIGN_ADMIN_VIEW"),
	CAMPAIGN_VIEW("PermissionEnum.CAMPAIGN_VIEW"),
	CAMPAIGN_START("PermissionEnum.CAMPAIGN_START"),
	CAMPAIGN_REPORT_VIEW("PermissionEnum.CAMPAIGN_REPORT_VIEW"),

	/* Permisos relacionados con la administracion de autores */
	AUTHORS_ADMIN_INSERT("PermissionEnum.AUTHORS_ADMIN_INSERT"),
	AUTHORS_ADMIN_UPDATE("PermissionEnum.AUTHORS_ADMIN_UPDATE"),
	AUTHORS_ADMIN_DELETE("PermissionEnum.AUTHORS_ADMIN_DELETE"),
	AUTHORS_ADMIN_VIEW("PermissionEnum.AUTHORS_ADMIN_VIEW"),

	/* Permisos relacionados con la consulta de campañas de los usuarios en sesion */
	MY_CAMPAIGN_VIEW("PermissionEnum.MY_CAMPAIGN_VIEW");

	private String viewKey;

	private PermissionEnum(String viewKey){
		this.viewKey = viewKey;
	}

	public String getViewKey() {
		return viewKey;
	}
}