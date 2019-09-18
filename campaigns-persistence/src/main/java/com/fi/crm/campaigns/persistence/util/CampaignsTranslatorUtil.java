package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.CpCampania;
import com.fi.crm.campaigns.persistence.model.CustomCpCampania;

public class CampaignsTranslatorUtil extends DTOTranslationUtil<CampaignDTO, CpCampania>{

	@Override
	public CpCampania translateObject(CampaignDTO dtoObject) throws TechnicalException {
			
		CpCampania cpCampania = new CpCampania();
		cpCampania.setAprobador(dtoObject.getApprover());
		cpCampania.setAsunto(dtoObject.getMailSubject());
		cpCampania.setAutorId(dtoObject.getAuthor().getAuthorId());
		cpCampania.setCampaniaId(dtoObject.getCampaignId());
		cpCampania.setUnidadNegocio(dtoObject.getBusinessUnit() != null ? dtoObject.getBusinessUnit().getCode() : null);
		cpCampania.setNoCiaUnidNeg(dtoObject.getBusinessUnit() != null ? dtoObject.getBusinessUnit().getNoCia() : null);
		cpCampania.setCreador(dtoObject.getUserCreator());
		cpCampania.setDescripcion(dtoObject.getDescription());
		cpCampania.setDominio(dtoObject.getDomain());
		cpCampania.setEstado(dtoObject.getStatus().getValue());
		cpCampania.setFinCampania(dtoObject.getEndDate());
		cpCampania.setFinEvento(dtoObject.getEventEndDate());
		cpCampania.setInicioCampania(dtoObject.getStartDate());
		cpCampania.setInicioEvento(dtoObject.getEventStartDate());
		
		cpCampania.setMarcacionAutomatica(dtoObject.getAutomaticCalling() != null ? new Boolean(dtoObject.getAutomaticCalling()).toString() : "false");
		cpCampania.setMensaje(dtoObject.getMessage());
		cpCampania.setNoCia(dtoObject.getStore() != null ? dtoObject.getStore().getNoCia() : null);
		cpCampania.setNombre(dtoObject.getName());
		cpCampania.setCentro(dtoObject.getStore() != null ? dtoObject.getStore().getCenter() : null);
		cpCampania.setTipo(dtoObject.getContactType().getValue());
		cpCampania.setMarcaCodigo(dtoObject.getBrand() != null ? dtoObject.getBrand().getCodigo() : null);
		cpCampania.setNoArti(dtoObject.getArticle() != null ? dtoObject.getArticle().getNoArti() : null);
		cpCampania.setJamesAnterior(dtoObject.getLastJames());
		return cpCampania;
	}

	@Override
	public List<CpCampania> translateList(List<CampaignDTO> dtoObjectList) throws TechnicalException {
		List<CpCampania> translatedtypeList = new ArrayList<CpCampania>();
		for (CampaignDTO incomingTypeElement : dtoObjectList) {
			translatedtypeList.add(translateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}

	@Override
	public CampaignDTO reverseTranslateObject(CpCampania modelObject) throws TechnicalException {
		
		CampaignDTO campaignDTO = new CampaignDTO();
		campaignDTO.setApprover(modelObject.getAprobador());
		campaignDTO.setAuthor(new AuthorDTO(modelObject.getAutorId()));
		campaignDTO.setAutomaticCalling(new Boolean(modelObject.getMarcacionAutomatica()));
		campaignDTO.setBusinessUnit(new BusinessUnitDTO(modelObject.getNoCiaUnidNeg(), modelObject.getUnidadNegocio()));
		campaignDTO.setCampaignId(modelObject.getCampaniaId());
		campaignDTO.setContactType(CampaignContactTypeEnum.byValue(modelObject.getTipo()));
		campaignDTO.setDescription(modelObject.getDescripcion());
		campaignDTO.setDomain(modelObject.getDominio());
		campaignDTO.setEndDate(modelObject.getFinCampania());
		campaignDTO.setEventEndDate(modelObject.getFinEvento());
		campaignDTO.setEventStartDate(modelObject.getInicioEvento());
		campaignDTO.setMailSubject(modelObject.getAsunto());
		campaignDTO.setMessage(modelObject.getMensaje());
		campaignDTO.setName(modelObject.getNombre());
		campaignDTO.setStartDate(modelObject.getInicioCampania());
		campaignDTO.setStatus(CampaignStatusEnum.byValue(modelObject.getEstado()));
		campaignDTO.setStore(new StoreDTO(modelObject.getNoCia(), modelObject.getCentro()));
		campaignDTO.setUserCreator(modelObject.getCreador());
		if(modelObject instanceof CustomCpCampania){
			campaignDTO.setEfectiveCount(((CustomCpCampania)modelObject).getEfectiveCount());
			campaignDTO.setTotalCount(((CustomCpCampania)modelObject).getTotalCount());
			campaignDTO.setSeenCount(((CustomCpCampania)modelObject).getSeenCount());
		}
		MarcasDTO brand = new MarcasDTO(modelObject.getNoCiaUnidNeg(), modelObject.getMarcaCodigo()); 
		campaignDTO.setBrand(brand);
		ArticleDTO article = new ArticleDTO(modelObject.getNoCiaUnidNeg(), modelObject.getNoArti()); 
		campaignDTO.setArticle(article);
		campaignDTO.setLastJames(modelObject.getJamesAnterior());
		return campaignDTO;
	}

	@Override
	public List<CampaignDTO> reverseTranslateObjectList(List<CpCampania> modelObjectList) throws TechnicalException {
		List<CampaignDTO> translatedtypeList = new ArrayList<CampaignDTO>();
		for (CpCampania incomingTypeElement : modelObjectList) {
			translatedtypeList.add(reverseTranslateObject(incomingTypeElement));
		}
		return translatedtypeList;
	}
}
