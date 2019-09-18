package com.fi.crm.campaigns.common.dto;

import java.util.Date;
import java.util.List;

import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;

public class AuditDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer auditId;
	private Date date;
	private AuditEventTypeEnum eventType;
	private String userId;
	private String terminal;
	private OperationStatusEnum status;
	private Integer entityId;
	private List<AuditDetailDTO> details;
	
	public Integer getAuditId() {
		return auditId;
	}
	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public AuditEventTypeEnum getEventType() {
		return eventType;
	}
	public void setEventType(AuditEventTypeEnum eventType) {
		this.eventType = eventType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public OperationStatusEnum getStatus() {
		return status;
	}
	public void setStatus(OperationStatusEnum status) {
		this.status = status;
	}
	public List<AuditDetailDTO> getDetails() {
		return details;
	}
	public void setDetails(List<AuditDetailDTO> details) {
		this.details = details;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
		
}
