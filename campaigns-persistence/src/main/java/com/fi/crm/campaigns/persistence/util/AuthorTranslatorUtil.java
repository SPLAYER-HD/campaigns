package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.PdUsuarioDTO;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpAutor;

public class AuthorTranslatorUtil extends DTOTranslationUtil<AuthorDTO, CpAutor> {

	@Override
	public CpAutor translateObject(AuthorDTO dtoObject) throws TechnicalException {
		CpAutor translatedModel = new CpAutor();
		translatedModel.setApellido1(dtoObject.getLastName1());
		translatedModel.setApellido2(dtoObject.getLastName2());
		translatedModel.setAutorId(dtoObject.getAuthorId());
		translatedModel.setNombres(dtoObject.getFirstName());
		translatedModel.setCorreo(dtoObject.getEmail());
		translatedModel.setStatus(dtoObject.getStatus());
		translatedModel.setCodUsuario(dtoObject.getPdusuario().getCodUsuario());
		return translatedModel;

	}

	@Override
	public List<CpAutor> translateList(List<AuthorDTO> dtoObjectList)
			throws TechnicalException {
		List<CpAutor> translatedModelList = new ArrayList<CpAutor>();
		for (AuthorDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;

	}

	@Override
	public AuthorDTO reverseTranslateObject(CpAutor modelObject) throws TechnicalException {
		AuthorDTO translatedDTO = new AuthorDTO();
		translatedDTO.setAuthorId(modelObject.getAutorId());
		translatedDTO.setFirstName(modelObject.getNombres());
		translatedDTO.setLastName1(modelObject.getApellido1());
		translatedDTO.setLastName2(modelObject.getApellido2());
		translatedDTO.setEmail(modelObject.getCorreo());
		translatedDTO.setFullName(modelObject.getNombres() 
				+ (modelObject.getApellido1() != null && !modelObject.getApellido1().trim().equals("") ? " " + modelObject.getApellido1().trim() : "")
				+ (modelObject.getApellido2() != null && !modelObject.getApellido2().trim().equals("") ? " " + modelObject.getApellido2().trim() : ""));
		translatedDTO.setStatus(modelObject.getStatus());
		translatedDTO.setPdusuario(new PdUsuarioDTO(ConstantsIdentifierEnum.DEFAULT_COMPANY.getDefaultValue(), modelObject.getCodUsuario()));
		return translatedDTO;
	}

	@Override
	public List<AuthorDTO> reverseTranslateObjectList(List<CpAutor> modelObjectList)
			throws TechnicalException {
		List<AuthorDTO> translatedDTOList = new ArrayList<AuthorDTO>();
		for (CpAutor modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;

	}

}
