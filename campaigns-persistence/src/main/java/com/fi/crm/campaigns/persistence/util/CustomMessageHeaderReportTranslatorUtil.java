package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CustomMessageHeaderReport;

public class CustomMessageHeaderReportTranslatorUtil extends
		DTOTranslationUtil<CustomMessageHeaderReportDTO, CustomMessageHeaderReport> {

	@Override
	public CustomMessageHeaderReport translateObject(
			CustomMessageHeaderReportDTO dtoObject) throws TechnicalException {
		throw new TechnicalException (ErrorCodesEnum.GENERIC_TECHNICAL_EXCEPTION);
	}

	@Override
	public List<CustomMessageHeaderReport> translateList(
			List<CustomMessageHeaderReportDTO> dtoObjectList)
			throws TechnicalException {
		throw new TechnicalException (ErrorCodesEnum.GENERIC_TECHNICAL_EXCEPTION);
	}

	@Override
	public CustomMessageHeaderReportDTO reverseTranslateObject(
			CustomMessageHeaderReport modelObject) throws TechnicalException {
		CustomMessageHeaderReportDTO dtoObject = new CustomMessageHeaderReportDTO();
		dtoObject.setName(modelObject.getNombre());
		dtoObject.setCampaignId(modelObject.getCampaniaId());
		dtoObject.setDescription(modelObject.getDescripcion());
		dtoObject.setAuthor(new AuthorDTO(modelObject.getAutorId()));
		dtoObject.setStartDate(modelObject.getInicioCampania());
		dtoObject.setEndDate(modelObject.getFinCampania());
		dtoObject.setEventStartDate(modelObject.getInicioEvento());
		dtoObject.setEventEndDate(modelObject.getFinEvento());
		dtoObject.setContactType(CampaignContactTypeEnum.valueOf(modelObject.getTipo()));
		dtoObject.setStatus(CampaignStatusEnum.valueOf(modelObject.getEstado()));
		dtoObject.setAutomaticCalling(Boolean.valueOf(modelObject.getMarcacionAutomatica()));
		dtoObject.setMessage(modelObject.getMensaje());
		dtoObject.setMailSubject(modelObject.getAsunto());
		dtoObject.setBusinessUnit(new BusinessUnitDTO(modelObject.getNoCiaUnidNeg(),modelObject.getUnidadNegocio()));
		dtoObject.setStore(new StoreDTO (modelObject.getNoCiaUnidNeg(),modelObject.getCentro()));
		dtoObject.setApprover(modelObject.getAprobador());
		dtoObject.setUserCreator(modelObject.getCreador());
		dtoObject.setDomain(modelObject.getDominio());
		dtoObject.setBrand(new MarcasDTO(modelObject.getNoCiaUnidNeg(),modelObject.getMarcaCodigo()));
		dtoObject.setArticle(new ArticleDTO(modelObject.getNoCiaUnidNeg(),modelObject.getNoArti()));
		dtoObject.setAsignadas(modelObject.getAsignadas());
		dtoObject.setRealizadas(modelObject.getRealizadas()==null?0:modelObject.getRealizadas());
		dtoObject.setPorcentajeRealizadas(modelObject.getPorcentajeRealizadas()==null?0:modelObject.getPorcentajeRealizadas());
		dtoObject.setEfectivas(modelObject.getEfectivas()==null?0:modelObject.getEfectivas());
		dtoObject.setPorcentajeEfectivas(modelObject.getPorcentajeEfectivas()==null?0:modelObject.getPorcentajeEfectivas());
		dtoObject.setNoEfectivas(modelObject.getNoEfectivas()==null?0:modelObject.getNoEfectivas());
		dtoObject.setPorcentajeNoEfectivas(modelObject.getPorcentajeNoEfectivas()==null?0:modelObject.getPorcentajeNoEfectivas());
	
		System.out.println (dtoObject);
		
		return dtoObject;
	}

	@Override
	public List<CustomMessageHeaderReportDTO> reverseTranslateObjectList(
			List<CustomMessageHeaderReport> modelObjectList)
			throws TechnicalException {
		List<CustomMessageHeaderReportDTO> result = new ArrayList<CustomMessageHeaderReportDTO>();
		for (CustomMessageHeaderReport c : modelObjectList) {
			result.add(reverseTranslateObject(c));
		}
		return result;
	}

}
