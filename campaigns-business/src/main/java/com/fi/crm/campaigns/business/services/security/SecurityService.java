package com.fi.crm.campaigns.business.services.security;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fi.crm.campaigns.business.services.audit.AuditServiceInterface;
import com.fi.crm.campaigns.business.util.AuditUtil;
import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.dto.LoginCredentialsDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.PvusuariosCustomMapper;
import com.fi.crm.campaigns.persistence.mappers.PvusuariosMapper;

public class SecurityService implements SecurityServiceInterface{

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(SecurityService.class);
	
	@Autowired
	private PvusuariosMapper pvusuariosMapper;
	@Autowired
	private PvusuariosCustomMapper pvusuariosCustomMapper;
	@Autowired
	private AuditServiceInterface auditService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserInfoDTO login(LoginCredentialsDTO credentials)
			throws BusinessException {
		logger.debug("Staring login method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.DELETE_CAMPAIGN, null);
		try {

			UserInfoDTO userInfo = pvusuariosCustomMapper.getUserByUserCode(credentials.getUserName());
			userInfo.setAppClient(credentials.getAppClient());
			userInfo.setTerminal(credentials.getTerminal());
			userInfo.setLoginTime(Calendar.getInstance());
			userInfo.setRegTime(Calendar.getInstance());
			userInfo.setUserCode(credentials.getUserName());
			
			audit.setUserId(credentials.getUserName());
			audit.setStatus(OperationStatusEnum.OK);
			
			return userInfo;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error login method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending login method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String encryptPassword(String password) throws BusinessException {
		logger.debug("Staring encryptPassword method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.DELETE_CAMPAIGN, null);
		try {
			String encrypt = pvusuariosCustomMapper.getPasswordEncrypted(password);
			audit.setStatus(OperationStatusEnum.OK);
			return encrypt;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error encryptPassword method ", e);
			throw new BusinessException(e, ErrorCodesEnum.PASSWORD_ENCRYPT_FAILED);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending encryptPassword method");
		}
	}
	
}
