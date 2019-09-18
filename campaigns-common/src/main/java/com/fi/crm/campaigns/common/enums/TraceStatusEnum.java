/**
 * 
 */
package com.fi.crm.campaigns.common.enums;

/**
 * @comapny FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 4/2015
 */
public enum TraceStatusEnum {

	/* Enum types */
	DEAD("DEAD","TraceStatusEnum.DEAD"),
	NOT_RESIDE("GET_TRACING","TraceStatusEnum.NOT_RESIDE"),
	WRONG_NUMBER("GET_TRACING","TraceStatusEnum.WRONG_NUMBER"),
	NOT_INTERESTED("GET_TRACING","TraceStatusEnum.NOT_INTERESTED");

	/* Local attributes */
	private String value;
	private String description;

	/**
	 * Private contructor for enum types
	 * @param value
	 * @param description
	 */
	private TraceStatusEnum(String value, String description){
		this.value = value;
		this.description = description;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}