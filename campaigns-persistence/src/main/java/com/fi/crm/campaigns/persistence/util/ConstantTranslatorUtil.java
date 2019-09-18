package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpConstante;

public class ConstantTranslatorUtil extends DTOTranslationUtil<ConstantDTO, CpConstante>{
	
	@Override
	public CpConstante translateObject(ConstantDTO dtoObject) throws TechnicalException {
			
		CpConstante constant = new CpConstante();
		constant.setConstanteId(dtoObject.getConstantId());
		constant.setDescripcion(dtoObject.getDescription());
		constant.setValor(dtoObject.getValue());
		return constant;
	}

	@Override
	public List<CpConstante> translateList(List<ConstantDTO> dtoObjectList) throws TechnicalException {
		List<CpConstante> translatedtypeList = new ArrayList<CpConstante>();
		for (ConstantDTO incomingTypeElement : dtoObjectList) {
			translatedtypeList.add(translateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}

	@Override
	public ConstantDTO reverseTranslateObject(CpConstante modelObject) throws TechnicalException {
		
		ConstantDTO constantDTO = new ConstantDTO();
		constantDTO.setConstantId(modelObject.getConstanteId());
		constantDTO.setDescription(modelObject.getDescripcion());
		constantDTO.setValue(modelObject.getValor());
		return constantDTO;
	}

	@Override
	public List<ConstantDTO> reverseTranslateObjectList(List<CpConstante> modelObjectList) throws TechnicalException {
		List<ConstantDTO> translatedtypeList = new ArrayList<ConstantDTO>();
		for (CpConstante incomingTypeElement : modelObjectList) {
			translatedtypeList.add(reverseTranslateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}
}
