package com.fi.crm.campaigns.business.services.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.dto.AuditDetailDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.CpAuditMapper;
import com.fi.crm.campaigns.persistence.mappers.CpDetalleAuditMapper;
import com.fi.crm.campaigns.persistence.mappers.SequencesCustomMapper;
import com.fi.crm.campaigns.persistence.model.CpAudit;
import com.fi.crm.campaigns.persistence.model.CpDetalleAudit;
import com.fi.crm.campaigns.persistence.util.AuditDetailTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.AuditTranslatorUtil;

public class AuditService implements AuditServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

	//mappers
	@Autowired
	private CpAuditMapper auditMapper;
	@Autowired
	private CpDetalleAuditMapper detalleAuditMapper;
	@Autowired
	private SequencesCustomMapper sequencesCustomMapper;

	//Translators
	private static final AuditTranslatorUtil AUDIT_TRANSLATOR_UTIL = new AuditTranslatorUtil();
	private static final AuditDetailTranslatorUtil AUDIT_DETAIL_TRANSLATOR_UTIL = new AuditDetailTranslatorUtil();

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void registerAudit(AuditDTO audit) throws BusinessException {
		logger.debug("Starting registerAudit");

		try {
			Integer id = sequencesCustomMapper.getAuditInfoSequence();
			audit.setAuditId(id);
			CpAudit auditModel = AUDIT_TRANSLATOR_UTIL.translateObject(audit);

			System.out.println("auditModel  "+auditModel.getCodUsuario());
			System.out.println("auditMapper == "+auditMapper);
			auditMapper.insert(auditModel);

			if ( audit.getDetails() != null ) {
				for ( AuditDetailDTO detail : audit.getDetails() ) {
					Integer idDetail = sequencesCustomMapper.getAuditDetailSequence();
					detail.setAuditId(idDetail);
					CpDetalleAudit auditDetail = AUDIT_DETAIL_TRANSLATOR_UTIL.translateObject(detail);
					auditDetail.setAuditId(auditModel.getAuditId());
					detalleAuditMapper.insert(auditDetail);
				}
			}
		} catch (Exception e) {
			logger.error("Error registering audit ", e);
			throw new BusinessException(e, ErrorCodesEnum.INSERTION_ERROR);
		} finally {
			logger.debug("Ending registerAudit");
		}
	}

}
