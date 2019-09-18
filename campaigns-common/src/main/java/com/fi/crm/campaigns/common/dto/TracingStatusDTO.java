package com.fi.crm.campaigns.common.dto;

import com.fi.crm.campaigns.common.enums.TracingStatusEnum;

public class TracingStatusDTO implements BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer statusId;

    private String name;

    private String description;

    private Boolean retry;

    private TracingStatusEnum type;

	public TracingStatusDTO() {
	}
	
	public TracingStatusDTO(Integer statusId) {
		super();
		this.statusId = statusId;
	}


	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRetry() {
		return retry;
	}

	public void setRetry(Boolean retry) {
		this.retry = retry;
	}

	public TracingStatusEnum getType() {
		return type;
	}

	public void setType(TracingStatusEnum type) {
		this.type = type;
	}

}