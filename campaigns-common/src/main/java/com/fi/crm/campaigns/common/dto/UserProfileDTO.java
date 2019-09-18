package com.fi.crm.campaigns.common.dto;

public class UserProfileDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer usuPerId;
    private ProfileDTO profile;
    private String codUser;
    
	public Integer getUsuPerId() {
		return usuPerId;
	}
	public void setUsuPerId(Integer usuPerId) {
		this.usuPerId = usuPerId;
	}
	public ProfileDTO getProfile() {
		return profile;
	}
	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}
	public String getCodUser() {
		return codUser;
	}
	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}
    
}
