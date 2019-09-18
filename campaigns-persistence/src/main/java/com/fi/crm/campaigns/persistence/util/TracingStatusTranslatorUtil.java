package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpEstadoSeguimiento;

public class TracingStatusTranslatorUtil extends DTOTranslationUtil<TracingStatusDTO, CpEstadoSeguimiento> {

	@Override
	public CpEstadoSeguimiento translateObject(TracingStatusDTO dtoObject) throws TechnicalException {
		CpEstadoSeguimiento translateObject = new CpEstadoSeguimiento();
		translateObject.setDescripcion(dtoObject.getDescription());
		translateObject.setEstadoId(dtoObject.getStatusId());
		translateObject.setNombre(dtoObject.getName());
		translateObject.setReintentar(dtoObject.getRetry().toString());
		translateObject.setTipo(dtoObject.getType().getValue());

		return translateObject;
	}

	@Override
	public List<CpEstadoSeguimiento> translateList(List<TracingStatusDTO> dtoObjectList)
			throws TechnicalException {
		List<CpEstadoSeguimiento> translatedModelList = new ArrayList<CpEstadoSeguimiento>();
		for (TracingStatusDTO dtoElement : dtoObjectList)
			translatedModelList.add(translateObject(dtoElement));
		return translatedModelList;
		
	}

	@Override
	public TracingStatusDTO reverseTranslateObject(CpEstadoSeguimiento modelObject) throws TechnicalException {
		
		TracingStatusDTO translatedDTO = new TracingStatusDTO();
		translatedDTO.setType(modelObject.getTipo() != null ? TracingStatusEnum.byValue(modelObject.getTipo()) : null);
		translatedDTO.setDescription(translatedDTO.getDescription());
		translatedDTO.setName(modelObject.getNombre());
		translatedDTO.setRetry(new Boolean(modelObject.getReintentar()));
		translatedDTO.setStatusId(modelObject.getEstadoId());
		
		return translatedDTO;
	}

	@Override
	public List<TracingStatusDTO> reverseTranslateObjectList(List<CpEstadoSeguimiento> modelObjectList)
			throws TechnicalException {
		List<TracingStatusDTO> translatedDTOList = new ArrayList<TracingStatusDTO>();
		for (CpEstadoSeguimiento modelElement : modelObjectList)
			translatedDTOList.add(reverseTranslateObject(modelElement));
		return translatedDTOList;
		
	}

}
