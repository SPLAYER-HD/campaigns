package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.ProfileDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpPerfil;

public class ProfileTranslatorUtil extends DTOTranslationUtil<ProfileDTO, CpPerfil> {

	@Override
	public CpPerfil translateObject(ProfileDTO dtoObject) throws TechnicalException {		
	
		CpPerfil translateObject = new CpPerfil();
		translateObject.setPerfilId(dtoObject.getProfileId());
		translateObject.setNombre(dtoObject.getName());
		
		return translateObject;
	}

	@Override
	public List<CpPerfil> translateList(List<ProfileDTO> dtoObjectList)
			throws TechnicalException {
		List<CpPerfil> translatedtypeList = new ArrayList<CpPerfil>();
		for (ProfileDTO incomingTypeElement : dtoObjectList) {
			translatedtypeList.add(translateObject(incomingTypeElement));
		}
		return translatedtypeList;
		
	}

	@Override
	public ProfileDTO reverseTranslateObject(CpPerfil modelObject)
			throws TechnicalException {
		
		ProfileDTO translatedDTO = new ProfileDTO();
		translatedDTO.setName(modelObject.getNombre());
		translatedDTO.setProfileId(modelObject.getPerfilId());
		
		return translatedDTO;
	}

	@Override
	public List<ProfileDTO> reverseTranslateObjectList(
			List<CpPerfil> modelObjectList) throws TechnicalException {
		List<ProfileDTO> translatedtypeList = new ArrayList<ProfileDTO>();
		for (CpPerfil incomingTypeElement : modelObjectList) {
			translatedtypeList.add(reverseTranslateObject(incomingTypeElement));
		}
		return translatedtypeList;
		
	}
}
