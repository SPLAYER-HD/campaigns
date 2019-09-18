package com.fi.crm.campaigns.common.dto;

public class ProfileDTO implements BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer profileId;
	private String name;
	
	public ProfileDTO() {
	}
	
	public ProfileDTO(Integer profileId) {
		super();
		this.profileId = profileId;
	}

	public Integer getProfileId() {
		return profileId;
	}
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
