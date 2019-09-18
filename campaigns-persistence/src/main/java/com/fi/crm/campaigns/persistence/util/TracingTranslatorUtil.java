package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.TracingDTO;
import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpSeguimiento;

public class TracingTranslatorUtil extends DTOTranslationUtil<TracingDTO, CpSeguimiento> {

	@Override
	public CpSeguimiento translateObject(TracingDTO dtoObject) throws TechnicalException {
		
		CpSeguimiento translateObject = new CpSeguimiento();
		translateObject.setCampaniaId(dtoObject.getCampaign().getCampaignId());
		translateObject.setClienteId(dtoObject.getClient().getClientId());
		translateObject.setDuracion(dtoObject.getDuration());
		translateObject.setEstado(dtoObject.getTracingStatus() != null ? dtoObject.getTracingStatus().getValue() : null);
		translateObject.setFechaUltEstado(dtoObject.getDateLastStatus());
		translateObject.setObservaciones(dtoObject.getObservations());
		translateObject.setSeguimientoId(dtoObject.getTracingId());
		translateObject.setSubestado(dtoObject.getTracingSubStatus() != null ?dtoObject.getTracingSubStatus().getStatusId() : null);
		translateObject.setUsuario(dtoObject.getUserCode());
		translateObject.setLlamando(dtoObject.getCalling().toString());
		return translateObject;		
	}

	@Override
	public List<CpSeguimiento> translateList(List<TracingDTO> dtoObjectList)
			throws TechnicalException {
		List<CpSeguimiento> translatedModelList = new ArrayList<CpSeguimiento>();
		for (TracingDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public TracingDTO reverseTranslateObject(CpSeguimiento modelObject) throws TechnicalException {
		TracingDTO translatedDTO = new TracingDTO();
		translatedDTO.setCampaign(new CampaignDTO(modelObject.getCampaniaId()));
		translatedDTO.setClient(new ClientCampaignDTO(modelObject.getClienteId()));
		translatedDTO.setDateLastStatus(modelObject.getFechaUltEstado());
		translatedDTO.setDuration(modelObject.getDuracion());
		translatedDTO.setObservations(modelObject.getObservaciones());
		translatedDTO.setTracingId(modelObject.getSeguimientoId());
		translatedDTO.setTracingStatus(modelObject.getEstado() != null ? TracingStatusEnum.byValue(modelObject.getEstado()) : null);
		translatedDTO.setTracingSubStatus(new TracingStatusDTO(modelObject.getSubestado()));
		translatedDTO.setUserCode(modelObject.getUsuario());
		translatedDTO.setCalling(new Boolean(modelObject.getLlamando()));
		return translatedDTO;
	}

	@Override
	public List<TracingDTO> reverseTranslateObjectList(List<CpSeguimiento> modelObjectList)
			throws TechnicalException {
		List<TracingDTO> translatedDTOList = new ArrayList<TracingDTO>();
		for (CpSeguimiento modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
