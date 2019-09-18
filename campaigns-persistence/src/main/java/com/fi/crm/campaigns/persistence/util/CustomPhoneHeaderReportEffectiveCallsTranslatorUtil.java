package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CustomPhoneHeaderReportEffectiveCalls;

public class CustomPhoneHeaderReportEffectiveCallsTranslatorUtil
		extends
		DTOTranslationUtil<CustomPhoneHeaderReportEffectiveCallsDTO, CustomPhoneHeaderReportEffectiveCalls> {

	@Override
	public CustomPhoneHeaderReportEffectiveCalls translateObject(
			CustomPhoneHeaderReportEffectiveCallsDTO dtoObject)
			throws TechnicalException {
		CustomPhoneHeaderReportEffectiveCalls modelObject = new CustomPhoneHeaderReportEffectiveCalls();
		modelObject.setCampaniaId(dtoObject.getCampaniaId());
		modelObject.setCantidad(dtoObject.getCantidad());
		modelObject.setNombre(dtoObject.getNombre());
		modelObject.setPorcentaje(dtoObject.getPorcentaje());
		return modelObject;
	}

	@Override
	public List<CustomPhoneHeaderReportEffectiveCalls> translateList(
			List<CustomPhoneHeaderReportEffectiveCallsDTO> dtoObjectList)
			throws TechnicalException {

		List<CustomPhoneHeaderReportEffectiveCalls> modelList = new ArrayList<CustomPhoneHeaderReportEffectiveCalls>();
		for (CustomPhoneHeaderReportEffectiveCallsDTO dtoObject : dtoObjectList) {
			modelList.add(translateObject(dtoObject));
		}
		return modelList;
	}

	@Override
	public CustomPhoneHeaderReportEffectiveCallsDTO reverseTranslateObject(
			CustomPhoneHeaderReportEffectiveCalls modelObject)
			throws TechnicalException {
		CustomPhoneHeaderReportEffectiveCallsDTO dtoObject = new CustomPhoneHeaderReportEffectiveCallsDTO();
		dtoObject.setCampaniaId(modelObject.getCampaniaId());
		dtoObject.setNombre(modelObject.getNombre());
		dtoObject.setCantidad(modelObject.getCantidad());
		dtoObject.setPorcentaje(modelObject.getPorcentaje());
		return dtoObject;
	}

	@Override
	public List<CustomPhoneHeaderReportEffectiveCallsDTO> reverseTranslateObjectList(
			List<CustomPhoneHeaderReportEffectiveCalls> modelObjectList)
			throws TechnicalException {
		List<CustomPhoneHeaderReportEffectiveCallsDTO> dtoObjectList = new ArrayList<CustomPhoneHeaderReportEffectiveCallsDTO>();
		for (CustomPhoneHeaderReportEffectiveCalls modelObject : modelObjectList) {
			dtoObjectList.add(reverseTranslateObject(modelObject));
		}
		return dtoObjectList;
	}

}
