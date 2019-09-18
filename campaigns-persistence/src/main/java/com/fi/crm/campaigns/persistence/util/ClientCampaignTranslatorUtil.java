package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;
import com.fi.crm.campaigns.common.enums.GenreEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpCliente;

public class ClientCampaignTranslatorUtil extends DTOTranslationUtil<ClientCampaignDTO, CpCliente> {

	@Override
	public CpCliente translateObject(ClientCampaignDTO dtoObject) throws TechnicalException {
		CpCliente translatedModel = new CpCliente();
		translatedModel.setApellido1(dtoObject.getLastName1());
		translatedModel.setApellido2(dtoObject.getLastName2());
		translatedModel.setCampaniaId(dtoObject.getCampaign().getCampaignId());
		translatedModel.setCelular(dtoObject.getCellPhone());
		translatedModel.setClienteId(dtoObject.getClientId());
		translatedModel.setCorreo(dtoObject.getEmail());
		translatedModel.setExtraFecha(dtoObject.getExtraDate());
		translatedModel.setExtraNum(dtoObject.getExtraNum());
		translatedModel.setExtraStr(dtoObject.getExtraStr());
		translatedModel.setGenero(dtoObject.getGenre().getValue());
		translatedModel.setIdentificacion(dtoObject.getDocumentNumber());
		translatedModel.setIndicativo(dtoObject.getIndicative());
		translatedModel.setNombres(dtoObject.getFirstName());
		translatedModel.setTelefono(dtoObject.getTelephone());
		translatedModel.setTipoIdentificacion(dtoObject.getDocumentType().getId());
		return translatedModel;
		
	}

	@Override
	public List<CpCliente> translateList(List<ClientCampaignDTO> dtoObjectList)
			throws TechnicalException {
		List<CpCliente> translatedModelList = new ArrayList<CpCliente>();
		for (ClientCampaignDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public ClientCampaignDTO reverseTranslateObject(CpCliente modelObject) throws TechnicalException {
		ClientCampaignDTO translatedDTO = new ClientCampaignDTO();
		translatedDTO.setCampaign(new CampaignDTO(modelObject.getCampaniaId()));
		translatedDTO.setCellPhone(modelObject.getCelular());
		translatedDTO.setClientId(modelObject.getClienteId());
		translatedDTO.setDocumentNumber(modelObject.getIdentificacion());
		translatedDTO.setDocumentType(new DocumentTypeDTO(modelObject.getTipoIdentificacion()));
		translatedDTO.setEmail(modelObject.getCorreo());
		translatedDTO.setExtraDate(modelObject.getExtraFecha());
		translatedDTO.setExtraNum(modelObject.getExtraNum());
		translatedDTO.setExtraStr(modelObject.getExtraStr());
		translatedDTO.setFirstName(modelObject.getNombres());
		translatedDTO.setGenre(GenreEnum.byValue(modelObject.getGenero()));
		translatedDTO.setLastName1(modelObject.getApellido1());
		translatedDTO.setLastName2(modelObject.getApellido2());
		translatedDTO.setTelephone(modelObject.getTelefono());
		translatedDTO.setIndicative(modelObject.getIndicativo());
		translatedDTO.setFullName(modelObject.getNombres() + (modelObject.getApellido1() != null ? " "+modelObject.getApellido1() : "") + 
				(modelObject.getApellido2() != null ? " "+modelObject.getApellido2() : ""));
		
		return translatedDTO;
	}

	@Override
	public List<ClientCampaignDTO> reverseTranslateObjectList(List<CpCliente> modelObjectList)
			throws TechnicalException {
		List<ClientCampaignDTO> translatedDTOList = new ArrayList<ClientCampaignDTO>();
		for (CpCliente modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
