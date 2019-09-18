/**
 * 
 */
package com.fi.crm.campaigns.common.enums;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 4/2015
 */
public enum GenreEnum {

	/* Enum types */
	MALE("M","GenreEnum.MALE"),
	FEMALE("F","GenreEnum.FEMALE");

	/* Local attributes */
	private String value;
	private String description;

	/**
	 * Private constructor for enum types
	 * @param value
	 * @param description
	 */
	private GenreEnum(String value, String description){
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

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static GenreEnum byValue(String value) {
		GenreEnum[] values = GenreEnum.values();
		for (int i = 0; i < values.length; i++) {
			if (values[i].value.equals(value))
				return values[i];
		}
		return null;
	}
}