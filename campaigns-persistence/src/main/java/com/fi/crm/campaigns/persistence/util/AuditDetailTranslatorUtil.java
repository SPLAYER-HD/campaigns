package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.AuditDetailDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpDetalleAudit;

public class AuditDetailTranslatorUtil extends DTOTranslationUtil<AuditDetailDTO, CpDetalleAudit> {

	@Override
	public CpDetalleAudit translateObject(AuditDetailDTO dtoObject)
			throws TechnicalException {
		CpDetalleAudit modelObject = new CpDetalleAudit();		
		modelObject.setDetalleId(dtoObject.getAuditDetailId());		
		modelObject.setAuditId(dtoObject.getAuditId());
		modelObject.setLlaveDetalle(dtoObject.getDetail());
		modelObject.setValorDetalle(dtoObject.getValue());
		return modelObject;
	}

	@Override
	public List<CpDetalleAudit> translateList(List<AuditDetailDTO> dtoObjectList)
			throws TechnicalException {
		List<CpDetalleAudit> translatedtypeList = new ArrayList<CpDetalleAudit>();
		for (AuditDetailDTO incomingTypeElement : dtoObjectList) {
			translatedtypeList.add(translateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}

	@Override
	public AuditDetailDTO reverseTranslateObject(CpDetalleAudit modelObject)
			throws TechnicalException {
		AuditDetailDTO dtoObject = new AuditDetailDTO();
		
		dtoObject.setAuditDetailId(modelObject.getDetalleId());
		dtoObject.setAuditId(modelObject.getAuditId());
		dtoObject.setDetail(modelObject.getLlaveDetalle());
		dtoObject.setValue(modelObject.getValorDetalle());
		return dtoObject;
	}

	@Override
	public List<AuditDetailDTO> reverseTranslateObjectList(
			List<CpDetalleAudit> modelObjectList) throws TechnicalException {
		List<AuditDetailDTO> translatedtypeList = new ArrayList<AuditDetailDTO>();
		for (CpDetalleAudit incomingTypeElement : modelObjectList) {
			translatedtypeList.add(reverseTranslateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}

}
