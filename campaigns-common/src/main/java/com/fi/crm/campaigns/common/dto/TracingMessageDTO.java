package com.fi.crm.campaigns.common.dto;

import java.util.Date;

import com.fi.crm.campaigns.common.enums.TracingMessageStatusEnum;

public class TracingMessageDTO implements BaseDTO{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4004692644895836132L;

	private Integer tracingMessageId;

    private ClientCampaignDTO client;

    private CampaignDTO campaign;

    private TracingMessageStatusEnum tracingMessageStatus;
    
    private Date dateLastStatus;
    
	public Integer getTracingMessageId() {
		return tracingMessageId;
	}

	public void setTracingMessageId(Integer tracingMessageId) {
		this.tracingMessageId = tracingMessageId;
	}

	public ClientCampaignDTO getClient() {
		return client;
	}

	public void setClient(ClientCampaignDTO client) {
		this.client = client;
	}

	public CampaignDTO getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignDTO campaign) {
		this.campaign = campaign;
	}

	public Date getDateLastStatus() {
		return dateLastStatus;
	}

	public void setDateLastStatus(Date dateLastStatus) {
		this.dateLastStatus = dateLastStatus;
	}

	public TracingMessageStatusEnum getTracingMessageStatus() {
		return tracingMessageStatus;
	}

	public void setTracingMessageStatus(TracingMessageStatusEnum tracingMessageStatus) {
		this.tracingMessageStatus = tracingMessageStatus;
	}

	@Override
	public String toString() {
		return "TracingMessageDTO [tracingMessageId=" + tracingMessageId + ", tracingMessageStatus=" + tracingMessageStatus + ", dateLastStatus="
				+ dateLastStatus + "]";
	}
 
}