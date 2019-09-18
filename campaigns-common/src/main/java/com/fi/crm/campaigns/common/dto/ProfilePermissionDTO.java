package com.fi.crm.campaigns.common.dto;


public class ProfilePermissionDTO implements BaseDTO{
	
	private ProfileDTO profile;
	private String action;

	public ProfileDTO getProfile() {
		return profile;
	}
	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
