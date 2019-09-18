package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CustomMessageClicksReport;

public class CustomMessageClicksReportTranslatorUtil extends
		DTOTranslationUtil<CustomMessageClicksReportDTO, CustomMessageClicksReport> {

	@Override
	public CustomMessageClicksReport translateObject(
			CustomMessageClicksReportDTO dtoObject) throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomMessageClicksReport> translateList(
			List<CustomMessageClicksReportDTO> dtoObjectList)
			throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomMessageClicksReportDTO reverseTranslateObject(
			CustomMessageClicksReport modelObject) throws TechnicalException {
		CustomMessageClicksReportDTO dtoObject = new CustomMessageClicksReportDTO();
		dtoObject.setTiempo(modelObject.getTiempo());
		dtoObject.setCantidad(modelObject.getCantidad());
		dtoObject.setRelativo(modelObject.getRelativo());
		dtoObject.setAbsoluto(modelObject.getAbsoluto());
		return dtoObject;
	}

	@Override
	public List<CustomMessageClicksReportDTO> reverseTranslateObjectList(
			List<CustomMessageClicksReport> modelObjectList)
			throws TechnicalException {
		List<CustomMessageClicksReportDTO> dtoObjectList = new ArrayList<CustomMessageClicksReportDTO>();
		for (CustomMessageClicksReport modelObject : modelObjectList){
			dtoObjectList.add(reverseTranslateObject(modelObject));
		}
		return dtoObjectList;
	}

}
