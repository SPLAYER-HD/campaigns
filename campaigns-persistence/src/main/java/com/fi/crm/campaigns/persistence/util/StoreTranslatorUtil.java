package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.Arincd;

public class StoreTranslatorUtil extends DTOTranslationUtil<StoreDTO, Arincd> {

	@Override
	public Arincd translateObject(StoreDTO dtoObject) throws TechnicalException {
		return null;
		
	}

	@Override
	public List<Arincd> translateList(List<StoreDTO> dtoObjectList)
			throws TechnicalException {
		List<Arincd> translatedModelList = new ArrayList<Arincd>();
		for (StoreDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public StoreDTO reverseTranslateObject(Arincd modelObject) throws TechnicalException {
		StoreDTO translatedDTO = new StoreDTO();
		translatedDTO.setCenter(modelObject.getCentro());
		translatedDTO.setName(modelObject.getNombre());
		translatedDTO.setNoCia(modelObject.getNoCia());
		return translatedDTO;
	}

	@Override
	public List<StoreDTO> reverseTranslateObjectList(List<Arincd> modelObjectList)
			throws TechnicalException {
		List<StoreDTO> translatedDTOList = new ArrayList<StoreDTO>();
		for (Arincd modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
