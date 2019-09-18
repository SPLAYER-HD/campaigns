package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CustomMessageEffectivenessReport;

public class CustomMessageEffectivenessReportTransalatorUtil
		extends
		DTOTranslationUtil<CustomMessageEffectivenessReportDTO, CustomMessageEffectivenessReport> {

	@Override
	public CustomMessageEffectivenessReport translateObject(
			CustomMessageEffectivenessReportDTO dtoObject)
			throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomMessageEffectivenessReport> translateList(
			List<CustomMessageEffectivenessReportDTO> dtoObjectList)
			throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomMessageEffectivenessReportDTO reverseTranslateObject(
			CustomMessageEffectivenessReport modelObject)
			throws TechnicalException {
		CustomMessageEffectivenessReportDTO dtoObject = new CustomMessageEffectivenessReportDTO();
		dtoObject.setUnidadesCliente(modelObject.getUnidadesCliente());
		dtoObject.setVentasCliente(modelObject.getVentasCliente());
		dtoObject.setUnidadesTotales(modelObject.getUnidadesTotales());
		dtoObject.setVentasTotales(modelObject.getVentasTotales());
		dtoObject.setPorcentajeUnidades(modelObject.getPorcentajeUnidades());
		dtoObject.setPorcentajeVentas(modelObject.getPorcentajeVentas());
		return dtoObject;
	}

	@Override
	public List<CustomMessageEffectivenessReportDTO> reverseTranslateObjectList(
			List<CustomMessageEffectivenessReport> modelObjectList)
			throws TechnicalException {
		List<CustomMessageEffectivenessReportDTO> dtoObjectList = new ArrayList<CustomMessageEffectivenessReportDTO>();
		for (CustomMessageEffectivenessReport modelObject: modelObjectList) {
			dtoObjectList.add(reverseTranslateObject(modelObject));
		}
		return dtoObjectList;
	}

}
