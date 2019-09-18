package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.ProfileDTO;
import com.fi.crm.campaigns.common.dto.ProfilePermissionDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpPermisoPerfilKey;

public class ProfilePermissionTranslatorUtil extends DTOTranslationUtil<ProfilePermissionDTO, CpPermisoPerfilKey> {

	@Override
	public CpPermisoPerfilKey translateObject(ProfilePermissionDTO dtoObject) throws TechnicalException {
		CpPermisoPerfilKey translatedModel = new CpPermisoPerfilKey();
		translatedModel.setAccion(dtoObject.getAction());
		translatedModel.setPerfilId(dtoObject.getProfile().getProfileId());
		return translatedModel;
		
	}

	@Override
	public List<CpPermisoPerfilKey> translateList(List<ProfilePermissionDTO> dtoObjectList)
			throws TechnicalException {
		List<CpPermisoPerfilKey> translatedModelList = new ArrayList<CpPermisoPerfilKey>();
		for (ProfilePermissionDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public ProfilePermissionDTO reverseTranslateObject(CpPermisoPerfilKey modelObject) throws TechnicalException {
		ProfilePermissionDTO translatedDTO = new ProfilePermissionDTO();
		translatedDTO.setAction(modelObject.getAccion());
		translatedDTO.setProfile(new ProfileDTO(modelObject.getPerfilId()));
		return translatedDTO;
	}

	@Override
	public List<ProfilePermissionDTO> reverseTranslateObjectList(List<CpPermisoPerfilKey> modelObjectList)
			throws TechnicalException {
		List<ProfilePermissionDTO> translatedDTOList = new ArrayList<ProfilePermissionDTO>();
		for (CpPermisoPerfilKey modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
