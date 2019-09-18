package com.fi.crm.campaigns.common.dto;

import java.util.Date;

import com.fi.crm.campaigns.common.enums.TracingStatusEnum;

public class TracingDTO implements BaseDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer tracingId;

    private ClientCampaignDTO client;

    private CampaignDTO campaign;

    private String group;

    private TracingStatusEnum tracingStatus;
    
    private TracingStatusDTO tracingSubStatus;

    private Date dateLastStatus;

    private String userCode;

    private String observations;

    private Integer duration;

    private Boolean calling;
    
	public Integer getTracingId() {
		return tracingId;
	}

	public void setTracingId(Integer tracingId) {
		this.tracingId = tracingId;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public TracingStatusEnum getTracingStatus() {
		return tracingStatus;
	}

	public void setTracingStatus(TracingStatusEnum tracingStatus) {
		this.tracingStatus = tracingStatus;
	}

	public TracingStatusDTO getTracingSubStatus() {
		return tracingSubStatus;
	}

	public void setTracingSubStatus(TracingStatusDTO tracingSubStatus) {
		this.tracingSubStatus = tracingSubStatus;
	}

	public Date getDateLastStatus() {
		return dateLastStatus;
	}

	public void setDateLastStatus(Date dateLastStatus) {
		this.dateLastStatus = dateLastStatus;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Boolean getCalling() {
		return calling;
	}

	public void setCalling(Boolean calling) {
		this.calling = calling;
	}

	@Override
	public String toString() {
		return "TracingDTO [tracingId=" + tracingId + ", client=" + client + ", campaign=" + campaign + ", group=" + group + ", tracingStatus="
				+ tracingStatus + ", tracingSubStatus=" + tracingSubStatus + ", dateLastStatus=" + dateLastStatus + ", userCode=" + userCode
				+ ", observations=" + observations + ", duration=" + duration + ", calling=" + calling + "]";
	}
 
}