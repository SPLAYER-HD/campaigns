package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CityDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;
import com.fi.crm.campaigns.common.dto.ProvinceDTO;
import com.fi.crm.campaigns.common.enums.GenreEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.Pvclientes;

public class ClientTranslatorUtil extends DTOTranslationUtil<ClientCampaignDTO, Pvclientes> {

	@Override
	public Pvclientes translateObject(ClientCampaignDTO dtoObject) throws TechnicalException {
		Pvclientes translatedModel = new Pvclientes();
		translatedModel.setfNacimiento(dtoObject.getBirthday());
		translatedModel.setTelefonoOficina(dtoObject.getCellPhone());
		translatedModel.setCanton(dtoObject.getCity() != null ? dtoObject.getCity().getId() : null);
		translatedModel.setApellido1(dtoObject.getLastName1());
		translatedModel.setApellido2(dtoObject.getLastName2());
		translatedModel.setIdIdentificacion(dtoObject.getDocumentNumber());
		translatedModel.setTipoIdentificacion(dtoObject.getDocumentType().getId());
		translatedModel.setEmail1(dtoObject.getEmail());
		translatedModel.setNombrePila(dtoObject.getFirstName());
		translatedModel.setSexo(dtoObject.getGenre().getValue());
		translatedModel.setTelefono(dtoObject.getTelephone());
		translatedModel.setProvincia(dtoObject.getProvince() != null ? dtoObject.getProvince().getId() : null);
		translatedModel.setCodCliente(dtoObject.getClientCode());
		translatedModel.setNoCia(dtoObject.getCompanyId());
		translatedModel.setCentrodCrea(dtoObject.getStoreId());
		translatedModel.setDireccion(dtoObject.getAddress());
		translatedModel.setIndicativo(dtoObject.getIndicative());
		translatedModel.setNombre(dtoObject.getFullName());
		return translatedModel;
		
	}

	@Override
	public List<Pvclientes> translateList(List<ClientCampaignDTO> dtoObjectList)
			throws TechnicalException {
		List<Pvclientes> translatedModelList = new ArrayList<Pvclientes>();
		for (ClientCampaignDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public ClientCampaignDTO reverseTranslateObject(Pvclientes modelObject) throws TechnicalException {
		ClientCampaignDTO translatedDTO = new ClientCampaignDTO();
		translatedDTO.setCellPhone(modelObject.getTelefonoOficina());
		translatedDTO.setDocumentNumber(modelObject.getIdIdentificacion());
		translatedDTO.setDocumentType(new DocumentTypeDTO(modelObject.getTipoIdentificacion()));
		translatedDTO.setEmail(modelObject.getEmail1());
		translatedDTO.setFirstName(modelObject.getNombrePila());
		translatedDTO.setGenre(GenreEnum.byValue(modelObject.getSexo()));
		translatedDTO.setLastName1(modelObject.getApellido1());
		translatedDTO.setLastName2(modelObject.getApellido2());
		translatedDTO.setTelephone(modelObject.getTelefono());
		translatedDTO.setBirthday(modelObject.getfNacimiento());
		translatedDTO.setCity(new CityDTO(modelObject.getCanton()));
		translatedDTO.setProvince(new ProvinceDTO(modelObject.getProvincia()));
		translatedDTO.setClientCode(modelObject.getCodCliente());
		translatedDTO.setCompanyId(modelObject.getNoCia());
		translatedDTO.setStoreId(modelObject.getCentrodCrea());
		translatedDTO.setAddress(modelObject.getDireccion());
		translatedDTO.setIndicative(modelObject.getIndicativo());
		translatedDTO.setFullName(modelObject.getNombre());
		return translatedDTO;
	}

	@Override
	public List<ClientCampaignDTO> reverseTranslateObjectList(List<Pvclientes> modelObjectList)
			throws TechnicalException {
		List<ClientCampaignDTO> translatedDTOList = new ArrayList<ClientCampaignDTO>();
		for (Pvclientes modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
