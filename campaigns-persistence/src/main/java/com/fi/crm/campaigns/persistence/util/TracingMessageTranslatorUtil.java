package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;
import com.fi.crm.campaigns.common.enums.TracingMessageStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpSeguimientoMensaje;

public class TracingMessageTranslatorUtil extends DTOTranslationUtil<TracingMessageDTO, CpSeguimientoMensaje> {

	@Override
	public CpSeguimientoMensaje translateObject(TracingMessageDTO dtoObject) throws TechnicalException {
		
		CpSeguimientoMensaje translateObject = new CpSeguimientoMensaje();
		translateObject.setCampaniaId(dtoObject.getCampaign().getCampaignId());
		translateObject.setClienteId(dtoObject.getClient().getClientId());
		translateObject.setEstado(dtoObject.getTracingMessageStatus().getValue());
		translateObject.setFechaUltEstad(dtoObject.getDateLastStatus());
		translateObject.setSegMensajeId(dtoObject.getTracingMessageId());
		return translateObject;		
	}

	@Override
	public List<CpSeguimientoMensaje> translateList(List<TracingMessageDTO> dtoObjectList)
			throws TechnicalException {
		List<CpSeguimientoMensaje> translatedModelList = new ArrayList<CpSeguimientoMensaje>();
		for (TracingMessageDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public TracingMessageDTO reverseTranslateObject(CpSeguimientoMensaje modelObject) throws TechnicalException {
		TracingMessageDTO translatedDTO = new TracingMessageDTO();
		translatedDTO.setCampaign(new CampaignDTO(modelObject.getCampaniaId()));
		translatedDTO.setClient(new ClientCampaignDTO(modelObject.getClienteId()));
		translatedDTO.setDateLastStatus(modelObject.getFechaUltEstad());
		translatedDTO.setTracingMessageId(modelObject.getSegMensajeId());
		translatedDTO.setTracingMessageStatus(modelObject.getEstado() != null ? TracingMessageStatusEnum.byValue(modelObject.getEstado()) : null);
		return translatedDTO;
	}

	@Override
	public List<TracingMessageDTO> reverseTranslateObjectList(List<CpSeguimientoMensaje> modelObjectList)
			throws TechnicalException {
		List<TracingMessageDTO> translatedDTOList = new ArrayList<TracingMessageDTO>();
		for (CpSeguimientoMensaje modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
