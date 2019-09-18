package com.fi.crm.campaigns.persistence.model;


public class CustomCpCampania extends CpCampania{
	
	private Integer efectiveCount;
	private Integer totalCount;
	private Integer seenCount;

	public Integer getEfectiveCount() {
		return efectiveCount;
	}
	public void setEfectiveCount(Integer efectiveCount) {
		this.efectiveCount = efectiveCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSeenCount() {
		return seenCount;
	}
	public void setSeenCount(Integer seenCount) {
		this.seenCount = seenCount;
	}
	
	
}