package com.fi.crm.campaigns.common.dto;

import java.util.Date;

public class ClientCampaignDTO extends ClientDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2554475385106869579L;
	
	private Integer clientId;
    private CampaignDTO campaign;       
    private String extraStr;
    private Integer extraNum;
    private Date extraDate;
    
	public ClientCampaignDTO() {
	}
	
	public ClientCampaignDTO(Integer clientId) {
		super();
		this.clientId = clientId;
	}

	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public CampaignDTO getCampaign() {
		return campaign;
	}
	public void setCampaign(CampaignDTO campaign) {
		this.campaign = campaign;
	}
	
	public String getExtraStr() {
		return extraStr;
	}
	public void setExtraStr(String extraStr) {
		this.extraStr = extraStr;
	}
	public Integer getExtraNum() {
		return extraNum;
	}
	public void setExtraNum(Integer extraNum) {
		this.extraNum = extraNum;
	}
	public Date getExtraDate() {
		return extraDate;
	}
	public void setExtraDate(Date extraDate) {
		this.extraDate = extraDate;
	}

	@Override
	public String toString() {
		return "ClientCampaignDTO [clientId=" + clientId + ", campaign=" + campaign + ", extraStr=" + extraStr + ", extraNum=" + extraNum
				+ ", extraDate=" + extraDate + "]";
	}	

}