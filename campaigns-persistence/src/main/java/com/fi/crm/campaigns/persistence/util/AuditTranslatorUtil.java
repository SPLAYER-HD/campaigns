package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpAudit;

public class AuditTranslatorUtil extends DTOTranslationUtil<AuditDTO, CpAudit> {

	@Override
	public CpAudit translateObject(AuditDTO dtoObject)
			throws TechnicalException {
		CpAudit model = new CpAudit();
		model.setAuditId(dtoObject.getAuditId());
		model.setFecha(dtoObject.getDate());
		model.setTipoEvento(dtoObject.getEventType().getValue());
		model.setEstado(dtoObject.getStatus().getValue());
		model.setTerminal(dtoObject.getTerminal());
		model.setEntidadId(dtoObject.getEntityId());
		model.setCodUsuario(dtoObject.getUserId());
		return model;
	}

	@Override
	public List<CpAudit> translateList(List<AuditDTO> dtoObjectList)
			throws TechnicalException {
		List<CpAudit> translatedtypeList = new ArrayList<CpAudit>();
		for (AuditDTO incomingTypeElement : dtoObjectList) {
			translatedtypeList.add(translateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}

	@Override
	public AuditDTO reverseTranslateObject(CpAudit modelObject)
			throws TechnicalException {
		AuditDTO dtoObject = new AuditDTO();
		dtoObject.setAuditId(modelObject.getAuditId());
		dtoObject.setDate(modelObject.getFecha());
		dtoObject.setEventType(AuditEventTypeEnum.byValue(modelObject.getTipoEvento()));
		dtoObject.setStatus(OperationStatusEnum.byValue(modelObject.getEstado()));
		dtoObject.setTerminal(modelObject.getTerminal());
		dtoObject.setEntityId(modelObject.getAuditId());
		dtoObject.setUserId(modelObject.getCodUsuario());
		
		return dtoObject;
	}

	@Override
	public List<AuditDTO> reverseTranslateObjectList(
			List<CpAudit> modelObjectList) throws TechnicalException {
		List<AuditDTO> translatedtypeList = new ArrayList<AuditDTO>();
		for (CpAudit incomingTypeElement : modelObjectList) {
			translatedtypeList.add(reverseTranslateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}
}
