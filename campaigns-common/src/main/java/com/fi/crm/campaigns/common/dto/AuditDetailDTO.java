package com.fi.crm.campaigns.common.dto;


public class AuditDetailDTO implements BaseDTO {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer auditDetailId;
	private String detail;
	private String value;
	private Integer auditId;

	public Integer getAuditDetailId() {
		return auditDetailId;
	}

	public void setAuditDetailId(Integer auditDetailId) {
		this.auditDetailId = auditDetailId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}
}
