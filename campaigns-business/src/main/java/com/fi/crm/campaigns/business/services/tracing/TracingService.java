package com.fi.crm.campaigns.business.services.tracing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fi.crm.campaigns.business.services.audit.AuditServiceInterface;
import com.fi.crm.campaigns.business.services.client.ClientServiceInterface;
import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.business.services.parametrization.ParametrizationServiceInterface;
import com.fi.crm.campaigns.business.util.AuditUtil;
import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;
import com.fi.crm.campaigns.common.dto.TracingDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;
import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.common.enums.GenreEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;
import com.fi.crm.campaigns.common.enums.TracingMessageStatusEnum;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.CpClienteCustomMapper;
import com.fi.crm.campaigns.persistence.mappers.CpSeguimientoMapper;
import com.fi.crm.campaigns.persistence.mappers.CpSeguimientoMensajeMapper;
import com.fi.crm.campaigns.persistence.mappers.CustomCpSeguimientoMapper;
import com.fi.crm.campaigns.persistence.mappers.CustomCpSeguimientoMensajeMapper;
import com.fi.crm.campaigns.persistence.mappers.SequencesCustomMapper;
import com.fi.crm.campaigns.persistence.model.CpCliente;
import com.fi.crm.campaigns.persistence.model.CpSeguimiento;
import com.fi.crm.campaigns.persistence.model.CpSeguimientoExample;
import com.fi.crm.campaigns.persistence.model.CpSeguimientoExample.Criteria;
import com.fi.crm.campaigns.persistence.model.CpSeguimientoMensaje;
import com.fi.crm.campaigns.persistence.model.CustomCpSeguimiento;
import com.fi.crm.campaigns.persistence.model.CustomCpSeguimientoMensaje;
import com.fi.crm.campaigns.persistence.util.ClientCampaignTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.TracingMessageTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.TracingTranslatorUtil;

public class TracingService implements TracingServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory.getLogger(TracingService.class);

	// Translators
	private static final ClientCampaignTranslatorUtil CLIENT_CAMPAIGN_TRANSLATOR_UTIL= new ClientCampaignTranslatorUtil();
	private static final TracingTranslatorUtil TRACING_TRANSLATOR_UTIL= new TracingTranslatorUtil();
	private static final TracingMessageTranslatorUtil TRACING_MESSAGE_TRANSLATOR_UTIL = new TracingMessageTranslatorUtil();

	//mappers
	@Autowired
	private SequencesCustomMapper sequencesCustomMapper;
	@Autowired
	private CpSeguimientoMapper seguimientoMapper;
	@Autowired
	private CpSeguimientoMensajeMapper seguimientoMensajeMapper;
	@Autowired
	private CpClienteCustomMapper clienteCustomMapper;
	@Autowired
	private ConstantServiceInterface constantService;
	@Autowired
	private ClientServiceInterface clientService;
	@Autowired
	private CustomCpSeguimientoMapper customSeguimientoMapper;
	@Autowired
	private CustomCpSeguimientoMensajeMapper customSeguimientoMensajeMapper;
	@Autowired
	private ParametrizationServiceInterface parametrizationService;
	@Autowired
	private AuditServiceInterface auditService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTracing(TracingDTO tracing, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring updateTracing method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.UPDATE_TRACING, userInfo);
		try {
			seguimientoMapper.updateByPrimaryKey(TRACING_TRANSLATOR_UTIL.translateObject(tracing));
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(tracing.getTracingId());
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error updateTracing method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending updateTracing method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TracingDTO getClientTracingByCampaign(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getClientTracingByCampaign method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_TRACING, userInfo);
		try {
			HashMap<String, ConstantDTO> map = constantService.getAllConstants();
			Integer hoursAfterLastStatus = Integer.valueOf(map.get(ConstantsIdentifierEnum.HOURS_AFTER_LAST_STATUS.getValue()).getValue());
			List<CpCliente> list = clienteCustomMapper.getClientsByCampaign(campaign.getCampaignId(), TracingStatusEnum.CONTACTED.getValue(), 
					hoursAfterLastStatus, TracingStatusEnum.NOT_CONTACTED.getValue());
			if(list != null && list.size()>0){
				ClientCampaignDTO clientCampaign = CLIENT_CAMPAIGN_TRANSLATOR_UTIL.reverseTranslateObject(list.get(0));
				ClientCampaignDTO client = clientService.getClientByDocumentTypeAndDocument(clientCampaign);
				TracingDTO tracing = getTracingByCampaign(campaign, clientCampaign, userInfo);
				if(tracing != null){
					tracing.setCalling(true);
					tracing.setUserCode(userInfo.getUserCode());
					tracing.setDateLastStatus(Calendar.getInstance().getTime());
					if(client == null){//The client doesn't exists
						tracing.setClient(clientCampaign);
					}else{ //The client already exists
						client.setClientId(clientCampaign.getClientId());
						//Update most recent data from CP_CLIENT
						client.setIndicative(clientCampaign.getIndicative());
						client.setTelephone(clientCampaign.getTelephone());
						client.setCellPhone(clientCampaign.getCellPhone());
						client.setFirstName(clientCampaign.getFirstName());
						client.setLastName1(clientCampaign.getLastName1());
						client.setLastName2(clientCampaign.getLastName2());
						client.setFullName(clientCampaign.getFullName());
						tracing.setClient(client);
					}
					this.updateTracing(tracing, userInfo);
				}else{
					
					tracing = new TracingDTO();
					tracing.setCalling(true);
					tracing.setCampaign(campaign);
					tracing.setUserCode(userInfo.getUserCode());
					tracing.setDateLastStatus(Calendar.getInstance().getTime());
					if(client == null){
						tracing.setClient(clientCampaign);
					}else{
						client.setClientId(clientCampaign.getClientId());
						//Updata most recent data from CP_CLIENT
						client.setIndicative(clientCampaign.getIndicative());
						client.setTelephone(clientCampaign.getTelephone());
						client.setCellPhone(clientCampaign.getCellPhone());
						client.setFirstName(clientCampaign.getFirstName());
						client.setLastName1(clientCampaign.getLastName1());
						client.setLastName2(clientCampaign.getLastName2());
						client.setFullName(clientCampaign.getFullName());
						tracing.setClient(client);
					}
					this.createTracing(tracing, userInfo);
					
				}
				
				audit.setStatus(OperationStatusEnum.OK);
				audit.setEntityId(tracing.getTracingId());
				return tracing;
			}else{
				audit.setStatus(OperationStatusEnum.OK);
				return null;
			}
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getClientTracingByCampaign method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getClientTracingByCampaign method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TracingDTO createTracing(TracingDTO tracing, UserInfoDTO userInfo) throws BusinessException{
		logger.debug("Staring createTracing method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.CREATE_TRACING, userInfo);
		try {
			Integer id = sequencesCustomMapper.getTracingSequence();
			tracing.setTracingId(id);
			tracing.setUserCode(userInfo.getUserCode());
			seguimientoMapper.insert(TRACING_TRANSLATOR_UTIL.translateObject(tracing));
			
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(tracing.getTracingId());
			return tracing;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error createTracing method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending createTracing method");
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TracingMessageDTO createTracingMessage(TracingMessageDTO tracingMessage) throws BusinessException{
		logger.debug("Staring createTracingMessage method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.CREATE_TRACING_MESSAGE, null);
		try {
			Integer id = sequencesCustomMapper.getTracingMessageSequence();
			tracingMessage.setTracingMessageId(id);
			seguimientoMensajeMapper.insert(TRACING_MESSAGE_TRANSLATOR_UTIL.translateObject(tracingMessage));
			
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(tracingMessage.getTracingMessageId());
			return tracingMessage;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error createTracingMessage method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending createTracingMessage method");
		}
	}
	
	@Override
	public TracingDTO getTracingByCampaign(CampaignDTO campaign, ClientCampaignDTO client, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getTracingByCampaign method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_TRACING, userInfo);
		try {
			CpSeguimientoExample example = new CpSeguimientoExample();
			com.fi.crm.campaigns.persistence.model.CpSeguimientoExample.Criteria criteria = example.createCriteria();
			criteria.andCampaniaIdEqualTo(campaign.getCampaignId());
			criteria.andClienteIdEqualTo(client.getClientId());
			List<CpSeguimiento> list = seguimientoMapper.selectByExample(example);

			audit.setStatus(OperationStatusEnum.OK);

			if(list != null && list.size() >0){
				audit.setEntityId(list.get(0).getSeguimientoId());
				return TRACING_TRANSLATOR_UTIL.reverseTranslateObject(list.get(0));
			}
			return null;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getTracingByCampaign method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getTracingByCampaign method");
		}
	}

	@Override
	public void freeCallsCustomer() throws BusinessException {
		logger.debug("Staring freeCallsCustomer method");
		try {
			CpSeguimientoExample example = new CpSeguimientoExample();
			Criteria criteria = example.createCriteria();
			criteria.andLlamandoEqualTo("true");
			List<CpSeguimiento> list = seguimientoMapper.selectByExample(example);
			for (CpSeguimiento cpSeguimiento : list) {
				cpSeguimiento.setLlamando("false");
				seguimientoMapper.updateByPrimaryKey(cpSeguimiento);
			}
		} catch (Exception e) {
			logger.error("Error freeCallsCustomer method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			logger.debug("Ending freeCallsCustomer method");
		}
	}

	@Override
	public void onloadMailByClient(Integer tracingMessageId) throws BusinessException {
		logger.debug("Staring updateTracing method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.UPDATE_TRACING_MESSAGE, null);
		try {
			CpSeguimientoMensaje seguimientoMensaje = new CpSeguimientoMensaje();
			seguimientoMensaje.setSegMensajeId(tracingMessageId);
			seguimientoMensaje.setFechaUltEstad(Calendar.getInstance().getTime());
			seguimientoMensaje.setEstado(TracingMessageStatusEnum.MSJ_SEEN.getValue());
			seguimientoMensajeMapper.updateByPrimaryKeySelective(seguimientoMensaje);
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(seguimientoMensaje.getSegMensajeId());
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error updateTracing method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending updateTracing method");
		}
		
	}
	
	@Override
	public List<TracingDTO> getTracingByCampaignId(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getTracingByCampaignId method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_TRACING_BY_CAMPAIGN_ID, null);
		try {
			List<CustomCpSeguimiento> customCpSeguimientos = customSeguimientoMapper.getTracingByCampaignId(campaign.getCampaignId());
			List<TracingDTO> list = new ArrayList<>();
			System.out.println("customCpSeguimientos >>"+customCpSeguimientos.size());
			for (CustomCpSeguimiento customCpSeguimiento : customCpSeguimientos) {
				TracingDTO tracing = new TracingDTO();
				tracing.setCampaign(new CampaignDTO(customCpSeguimiento.getCampaniaId()));
				tracing.setClient(new ClientCampaignDTO(customCpSeguimiento.getClienteId()));
				tracing.setDateLastStatus(customCpSeguimiento.getFechaUltEstado());
				tracing.setDuration(customCpSeguimiento.getDuracion());
				tracing.setObservations(customCpSeguimiento.getObservaciones());
				tracing.setTracingId(customCpSeguimiento.getSeguimientoId());
				tracing.setTracingStatus(customCpSeguimiento.getEstado() != null ? TracingStatusEnum.byValue(customCpSeguimiento.getEstado()) : null);
				tracing.setTracingSubStatus(new TracingStatusDTO(customCpSeguimiento.getSubestado()));
				tracing.setUserCode(customCpSeguimiento.getUsuario());
				tracing.setCalling(new Boolean(customCpSeguimiento.getLlamando()));
				
				ClientCampaignDTO client = new ClientCampaignDTO();
				client.setCampaign(new CampaignDTO(customCpSeguimiento.getCampaniaId()));
				client.setCellPhone(customCpSeguimiento.getCelular());
				client.setClientId(customCpSeguimiento.getClienteId());
				client.setDocumentNumber(customCpSeguimiento.getIdentificacion());
				client.setDocumentType(new DocumentTypeDTO(customCpSeguimiento.getTipoIdentificacion()));
				client.setEmail(customCpSeguimiento.getCorreo());
				client.setExtraDate(customCpSeguimiento.getExtraFecha());
				client.setExtraNum(customCpSeguimiento.getExtraNum());
				client.setExtraStr(customCpSeguimiento.getExtraStr());
				client.setFirstName(customCpSeguimiento.getNombres());
				client.setGenre(GenreEnum.byValue(customCpSeguimiento.getGenero()));
				client.setLastName1(customCpSeguimiento.getApellido1());
				client.setLastName2(customCpSeguimiento.getApellido2());
				client.setTelephone(customCpSeguimiento.getTelefono());
				client.setIndicative(customCpSeguimiento.getIndicativo());
				client.setFullName(customCpSeguimiento.getNombres() + (customCpSeguimiento.getApellido1() != null ? " "+customCpSeguimiento.getApellido1() : "") + 
						(customCpSeguimiento.getApellido2() != null ? " "+customCpSeguimiento.getApellido2() : ""));
				
				tracing.setClient(client);
				if(tracing.getTracingSubStatus().getStatusId() != null){
					tracing.setTracingSubStatus(parametrizationService.getTracingStatusById(tracing.getTracingSubStatus(), userInfo));
				}
				list.add(tracing);
			}
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(campaign.getCampaignId());
			return list;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getTracingByCampaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getTracingByCampaignId method");
		}
		
	}
	
	@Override
	public List<TracingMessageDTO> getTracingMessageByCampaignId(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getTracingMessageByCampaignId method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_TRACING_MESSAGE_BY_CAMPAIGN_ID, null);
		try {
			List<CustomCpSeguimientoMensaje> customCpSeguimientoMensajes = customSeguimientoMensajeMapper.getTracingMessageByCampaignId(campaign.getCampaignId());
			List<TracingMessageDTO> list = new ArrayList<>();
			for (CustomCpSeguimientoMensaje customCpSeguimientoMensaje : customCpSeguimientoMensajes) {
				TracingMessageDTO tracingMessage = new TracingMessageDTO();
				tracingMessage.setCampaign(new CampaignDTO(customCpSeguimientoMensaje.getCampaniaId()));
				tracingMessage.setClient(new ClientCampaignDTO(customCpSeguimientoMensaje.getClienteId()));
				tracingMessage.setDateLastStatus(customCpSeguimientoMensaje.getFechaUltEstad());
				tracingMessage.setTracingMessageId(customCpSeguimientoMensaje.getSegMensajeId());
				tracingMessage.setTracingMessageStatus(customCpSeguimientoMensaje.getEstado() != null ? TracingMessageStatusEnum.byValue(customCpSeguimientoMensaje.getEstado()) : null);
				
				ClientCampaignDTO client = new ClientCampaignDTO();
				client.setCampaign(new CampaignDTO(customCpSeguimientoMensaje.getCampaniaId()));
				client.setCellPhone(customCpSeguimientoMensaje.getCelular());
				client.setClientId(customCpSeguimientoMensaje.getClienteId());
				client.setDocumentNumber(customCpSeguimientoMensaje.getIdentificacion());
				client.setDocumentType(new DocumentTypeDTO(customCpSeguimientoMensaje.getTipoIdentificacion()));
				client.setEmail(customCpSeguimientoMensaje.getCorreo());
				client.setExtraDate(customCpSeguimientoMensaje.getExtraFecha());
				client.setExtraNum(customCpSeguimientoMensaje.getExtraNum());
				client.setExtraStr(customCpSeguimientoMensaje.getExtraStr());
				client.setFirstName(customCpSeguimientoMensaje.getNombres());
				client.setGenre(GenreEnum.byValue(customCpSeguimientoMensaje.getGenero()));
				client.setLastName1(customCpSeguimientoMensaje.getApellido1());
				client.setLastName2(customCpSeguimientoMensaje.getApellido2());
				client.setTelephone(customCpSeguimientoMensaje.getTelefono());
				client.setIndicative(customCpSeguimientoMensaje.getIndicativo());
				client.setFullName(customCpSeguimientoMensaje.getNombres() + (customCpSeguimientoMensaje.getApellido1() != null ? " "+customCpSeguimientoMensaje.getApellido1() : "") + 
						(customCpSeguimientoMensaje.getApellido2() != null ? " "+customCpSeguimientoMensaje.getApellido2() : ""));
				
				tracingMessage.setClient(client);
				list.add(tracingMessage);
			}
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(campaign.getCampaignId());
			return list;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getTracingMessageByCampaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getTracingMessageByCampaignId method");
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTracingMessage(TracingMessageDTO tracing, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring updateTracingMessage method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.UPDATE_TRACING, userInfo);
		try {
			seguimientoMensajeMapper.updateByPrimaryKey(TRACING_MESSAGE_TRANSLATOR_UTIL.translateObject(tracing));
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(tracing.getTracingMessageId());
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error updateTracingMessage method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending updateTracingMessage method");
		}
	}

}
