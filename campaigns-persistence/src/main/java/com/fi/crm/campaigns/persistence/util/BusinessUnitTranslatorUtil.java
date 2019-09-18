package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.Arunneg;

public class BusinessUnitTranslatorUtil extends DTOTranslationUtil<BusinessUnitDTO, Arunneg> {

	@Override
	public Arunneg translateObject(BusinessUnitDTO dtoObject) throws TechnicalException {
		return null;
		
	}

	@Override
	public List<Arunneg> translateList(List<BusinessUnitDTO> dtoObjectList)
			throws TechnicalException {
		List<Arunneg> translatedModelList = new ArrayList<Arunneg>();
		for (BusinessUnitDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public BusinessUnitDTO reverseTranslateObject(Arunneg modelObject) throws TechnicalException {
		BusinessUnitDTO translatedDTO = new BusinessUnitDTO();
		translatedDTO.setCode(modelObject.getCodigo());
		translatedDTO.setNoCia(modelObject.getNoCia());
		translatedDTO.setName(modelObject.getNombre());
		return translatedDTO;
	}

	@Override
	public List<BusinessUnitDTO> reverseTranslateObjectList(List<Arunneg> modelObjectList)
			throws TechnicalException {
		List<BusinessUnitDTO> translatedDTOList = new ArrayList<BusinessUnitDTO>();
		for (Arunneg modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
