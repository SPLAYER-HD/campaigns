package com.fi.crm.campaigns.common.enums;

public enum InterfaceActionModeEnum {

	NEW_MODE ("NEW_MODE","InterfaceActionMode.NEW_MODE"),
	EDIT_MODE ("EDIT_MODE","InterfaceActionMode.EDIT_MODE"),
	SEARCH_MODE ("SEARCH_MODE","InterfaceActionMode.SEARCH_MODE"),;
	
	private String value;
	private String viewKey;
	
	private InterfaceActionModeEnum (String value, String viewKey){
		this.value = value;
		this.viewKey = viewKey;
	}
	
	public String getValue (){
		return this.value;
	}
	
	public String getViewKey (){
		return this.viewKey;
	}
	
	public static InterfaceActionModeEnum byValue (String value){
		if (value == null)
			throw new NullPointerException();
		if (value.isEmpty())
			throw new IllegalArgumentException();
		for (InterfaceActionModeEnum enumElement : InterfaceActionModeEnum.values())
			if (value.equals(enumElement.getValue()))
				return enumElement;
		return null;
	}
}
