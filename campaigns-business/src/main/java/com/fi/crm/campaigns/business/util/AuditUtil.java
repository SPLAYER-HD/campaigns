package com.fi.crm.campaigns.business.util;

import java.util.Calendar;

import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;

public class AuditUtil {
	public static AuditDTO buildBasicAudit(AuditEventTypeEnum event, UserInfoDTO userInfo) {
		AuditDTO audit = new AuditDTO();
		audit.setDate(Calendar.getInstance().getTime());
		if ( userInfo == null ) {
			audit.setUserId("NON USER");
			audit.setTerminal("127.0.0.1");
		} else {
			audit.setUserId(userInfo.getUserCode());
			audit.setTerminal(userInfo.getTerminal());
		}
		
		audit.setStatus(OperationStatusEnum.ERROR);
		audit.setEventType(event);
		return audit;
	}
}
