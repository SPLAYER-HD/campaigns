package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CustomMessageDetailReportDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CustomMessageDetailReport;

public class CustomMessageDetailReportTranslatorUtil extends
		DTOTranslationUtil<CustomMessageDetailReportDTO, CustomMessageDetailReport> {

	@Override
	public CustomMessageDetailReport translateObject(
			CustomMessageDetailReportDTO dtoObject) throws TechnicalException {

		CustomMessageDetailReport modelObject = new CustomMessageDetailReport();
		modelObject.setCampaniaId(dtoObject.getCampaniaId());
		modelObject.setTelefono(dtoObject.getTelefono());
		modelObject.setCelular(dtoObject.getCelular());
		modelObject.setNombre(dtoObject.getNombre());
		modelObject.setCorreo(dtoObject.getCorreo());
		modelObject.setEstado(dtoObject.getEstado());
		modelObject.setObservaciones(dtoObject.getObservaciones());
		return modelObject;
	}

	@Override
	public List<CustomMessageDetailReport> translateList(
			List<CustomMessageDetailReportDTO> dtoObjectList)
			throws TechnicalException {

		List<CustomMessageDetailReport> customMessageDetailReportList = new ArrayList<CustomMessageDetailReport>();
		for (CustomMessageDetailReportDTO dtoObject : dtoObjectList) {
			customMessageDetailReportList.add(translateObject(dtoObject));
		}
		return customMessageDetailReportList;
	}

	@Override
	public CustomMessageDetailReportDTO reverseTranslateObject(
			CustomMessageDetailReport modelObject) throws TechnicalException {
		
		CustomMessageDetailReportDTO dtoObject = new CustomMessageDetailReportDTO();
		
		dtoObject.setCampaniaId(modelObject.getCampaniaId());
		dtoObject.setTelefono(modelObject.getTelefono());
		dtoObject.setCelular(modelObject.getCelular());
		dtoObject.setNombre(modelObject.getNombre());
		dtoObject.setCorreo(modelObject.getCorreo());
		dtoObject.setEstado(modelObject.getEstado());
		dtoObject.setObservaciones(modelObject.getObservaciones());
		return dtoObject;
	}

	@Override
	public List<CustomMessageDetailReportDTO> reverseTranslateObjectList(
			List<CustomMessageDetailReport> modelObjectList)
			throws TechnicalException {
		List<CustomMessageDetailReportDTO> customMessageDetailReportDTOList = new ArrayList<CustomMessageDetailReportDTO>();
		for (CustomMessageDetailReport modelObject : modelObjectList) {
			customMessageDetailReportDTOList.add(reverseTranslateObject(modelObject));
		}
		return customMessageDetailReportDTOList;
	}

}
