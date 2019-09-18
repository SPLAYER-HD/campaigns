package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CustomPhoneDetailReportDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CustomPhoneDetailReport;

public class CustomPhoneDetailReportTranslatorUtil extends
		DTOTranslationUtil<CustomPhoneDetailReportDTO, CustomPhoneDetailReport> {

	@Override
	public CustomPhoneDetailReport translateObject(
			CustomPhoneDetailReportDTO dtoObject) throws TechnicalException {

		CustomPhoneDetailReport modelObject = new CustomPhoneDetailReport();
		modelObject.setCampaniaId(dtoObject.getCampaniaId());
		modelObject.setTelefono(dtoObject.getTelefono());
		modelObject.setCelular(dtoObject.getCelular());
		modelObject.setNombre(dtoObject.getNombre());
		modelObject.setEstado(dtoObject.getEstado());
		modelObject.setObservaciones(dtoObject.getObservaciones());
		return modelObject;
	}

	@Override
	public List<CustomPhoneDetailReport> translateList(
			List<CustomPhoneDetailReportDTO> dtoObjectList)
			throws TechnicalException {

		List<CustomPhoneDetailReport> customPhoneDetailReportList = new ArrayList<CustomPhoneDetailReport>();
		for (CustomPhoneDetailReportDTO dtoObject : dtoObjectList) {
			customPhoneDetailReportList.add(translateObject(dtoObject));
		}
		return customPhoneDetailReportList;
	}

	@Override
	public CustomPhoneDetailReportDTO reverseTranslateObject(
			CustomPhoneDetailReport modelObject) throws TechnicalException {
		
		CustomPhoneDetailReportDTO dtoObject = new CustomPhoneDetailReportDTO();
		
		dtoObject.setCampaniaId(modelObject.getCampaniaId());
		dtoObject.setTelefono(modelObject.getTelefono());
		dtoObject.setCelular(modelObject.getCelular());
		dtoObject.setNombre(modelObject.getNombre());
		dtoObject.setEstado(modelObject.getEstado());
		dtoObject.setObservaciones(modelObject.getObservaciones());
		return dtoObject;
	}

	@Override
	public List<CustomPhoneDetailReportDTO> reverseTranslateObjectList(
			List<CustomPhoneDetailReport> modelObjectList)
			throws TechnicalException {
		List<CustomPhoneDetailReportDTO> customPhoneDetailReportDTOList = new ArrayList<CustomPhoneDetailReportDTO>();
		for (CustomPhoneDetailReport modelObject : modelObjectList) {
			customPhoneDetailReportDTOList.add(reverseTranslateObject(modelObject));
		}
		return customPhoneDetailReportDTOList;
	}

}
