package com.fi.crm.campaigns.business.services.audit;

import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;

public interface AuditServiceInterface {
	
	public void registerAudit(AuditDTO audit) throws BusinessException;
}
