package com.fi.crm.campaigns.business.services.campaign;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fi.crm.campaigns.business.jms.JmsMessageProducerInterface;
import com.fi.crm.campaigns.business.services.audit.AuditServiceInterface;
import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.business.services.parametrization.ParametrizationServiceInterface;
import com.fi.crm.campaigns.business.services.report.ReportServiceInterface;
import com.fi.crm.campaigns.business.services.tracing.TracingServiceInterface;
import com.fi.crm.campaigns.business.util.AuditUtil;
import com.fi.crm.campaigns.business.util.PdfReportGenerator;
import com.fi.crm.campaigns.business.util.ValidatorUtil;
import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.common.dto.JamesDataDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;
import com.fi.crm.campaigns.common.enums.TracingMessageStatusEnum;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.infrastructure.mail.SendEmail;
import com.fi.crm.campaigns.persistence.mappers.CpCampaniaCustomMapper;
import com.fi.crm.campaigns.persistence.mappers.CpCampaniaMapper;
import com.fi.crm.campaigns.persistence.mappers.CpClienteCustomMapper;
import com.fi.crm.campaigns.persistence.mappers.PdUsuarioMapper;
import com.fi.crm.campaigns.persistence.mappers.SequencesCustomMapper;
import com.fi.crm.campaigns.persistence.model.CpCampania;
import com.fi.crm.campaigns.persistence.model.CpCampaniaExample;
import com.fi.crm.campaigns.persistence.model.CpCampaniaExample.Criteria;
import com.fi.crm.campaigns.persistence.model.CpCliente;
import com.fi.crm.campaigns.persistence.model.CustomCpCampania;
import com.fi.crm.campaigns.persistence.model.PdUsuario;
import com.fi.crm.campaigns.persistence.util.CampaignsTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.ClientCampaignTranslatorUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CampaignsService implements CampaignsServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CampaignsService.class);

	// Translators
	private static final CampaignsTranslatorUtil CAMPAIGNS_TRANSLATOR = new CampaignsTranslatorUtil();
	private static final ClientCampaignTranslatorUtil CLIENT_CAMPAIGN_TRANSLATOR_UTIL = new ClientCampaignTranslatorUtil();

	// mappers
	@Autowired
	private CpCampaniaMapper campaniaMapper;
	@Autowired
	private SequencesCustomMapper sequencesCustomMapper;
	@Autowired
	private CpCampaniaCustomMapper campaniaCustomMapper;
	@Autowired
	private PdUsuarioMapper pdUsuarioMapper;
	@Autowired
	private ConstantServiceInterface constantService;
	@Autowired
	private ParametrizationServiceInterface parametrizationService;
	@Autowired
	private CpClienteCustomMapper clienteCustomMapper;
	@Autowired
	private AuditServiceInterface auditService;
	@Autowired
	private TracingServiceInterface tracingService;
	@Autowired
	private JmsMessageProducerInterface jmsMessageProducer;

	@Autowired
	private ReportServiceInterface reportService;

	@Override
	public CampaignDTO getCampaignById(CampaignDTO campaign,
			UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getCampaignById method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.GET_CAMPAIGNS, userInfo);
		try {
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(campaign.getCampaignId());
			return CAMPAIGNS_TRANSLATOR.reverseTranslateObject(campaniaMapper
					.selectByPrimaryKey(campaign.getCampaignId()));
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getCampaignById method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getCampaignById method");
		}
	}

	@Override
	public List<CampaignDTO> getCampaignsByStateAndDate(CampaignDTO campaign,
			UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getCampaignsByStateAndDate method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.GET_CAMPAIGNS, userInfo);
		try {
			Date startDate;
			if (campaign.getStartDate() != null) {
				startDate = campaign.getStartDate();
			} else {
				HashMap<String, ConstantDTO> map = constantService
						.getAllConstants();
				Integer months = Integer.valueOf(map.get(
						ConstantsIdentifierEnum.NUMBERS_MONTHS_HISTORY
								.getValue()).getValue());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.MONTH, -months);
				startDate = calendar.getTime();
			}

			List<CustomCpCampania> campanias = campaniaCustomMapper
					.selectByStateAndDate(
							TracingStatusEnum.CONTACTED.getValue(),
							TracingMessageStatusEnum.MSJ_SEEN.getValue(),
							TracingMessageStatusEnum.MSJ_SENT.getValue(),
							startDate, campaign.getEndDate(),
							campaign.getStatus());
			List<CampaignDTO> list = new ArrayList<>();
			audit.setStatus(OperationStatusEnum.OK);
			for (CustomCpCampania cpCampania : campanias) {
				CampaignDTO dto = CAMPAIGNS_TRANSLATOR
						.reverseTranslateObject(cpCampania);
				if (cpCampania.getNoCia() != null
						&& !cpCampania.getNoCia().trim().isEmpty()) {
					dto.setStore(parametrizationService.getStoreById(
							cpCampania.getNoCia(), cpCampania.getCentro(),
							userInfo));
				}
				if (cpCampania.getUnidadNegocio() != null
						&& !cpCampania.getUnidadNegocio().trim().isEmpty()) {
					dto.setBusinessUnit(parametrizationService
							.getBusinessUnitById(cpCampania.getUnidadNegocio(),
									cpCampania.getNoCiaUnidNeg(), userInfo));
				}

				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getCampaignsByStateAndDate method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getCampaignsByStateAndDate method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CampaignDTO createCampaign(CampaignDTO campaign, UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring createCampaign method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.CREATE_CAMPAIGN, userInfo);
		try {
			Integer id = sequencesCustomMapper.getCampaignSequence();
			campaign.setCampaignId(id);
			campaign.setUserCreator(userInfo.getUserCode());
			System.out.println("campaign id ==>> " + id);
			CpCampania campania = CAMPAIGNS_TRANSLATOR
					.translateObject(campaign);
			campaniaMapper.insert(campania);

			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(campania.getCampaniaId());

			return CAMPAIGNS_TRANSLATOR.reverseTranslateObject(campania);

		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error createCampaign method ", e);
			throw new BusinessException(e, ErrorCodesEnum.INSERTION_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending createCampaign method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CampaignDTO updateCampaign(CampaignDTO campaign, UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring updateCampaign method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.UPDATE_CAMPAIGN, userInfo);
		try {
			campaign.setUserCreator(userInfo.getUserCode());
			CpCampania campania = CAMPAIGNS_TRANSLATOR
					.translateObject(campaign);
			campaniaMapper.updateByPrimaryKey(campania);

			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(campaign.getCampaignId());

			return CAMPAIGNS_TRANSLATOR.reverseTranslateObject(campania);

		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error updateCampaign method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending updateCampaign method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteCampaign(CampaignDTO campaign, UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring deleteCampaign method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.DELETE_CAMPAIGN, userInfo);
		try {
			campaniaMapper.deleteByPrimaryKey(campaign.getCampaignId());
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(campaign.getCampaignId());

		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error deleteCampaign method ", e);
			throw new BusinessException(e,
					ErrorCodesEnum.DELETE_NOT_POSSIBLE_BY_REFERENCE);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending deleteCampaign method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void startCampaign() throws BusinessException {
		logger.debug("Staring startCampaign method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.GET_CAMPAIGNS, null);
		try {
			HashMap<String, ConstantDTO> map = constantService
					.getAllConstants();
			CpCampaniaExample example = new CpCampaniaExample();
			Criteria criteria = example.createCriteria();
			criteria.andEstadoEqualTo(CampaignStatusEnum.APPROVED.getValue());
			criteria.andFinCampaniaGreaterThanOrEqualTo(Calendar.getInstance()
					.getTime());
			criteria.andInicioCampaniaLessThanOrEqualTo(Calendar.getInstance()
					.getTime());
			List<String> values = new ArrayList<>();
			values.add(CampaignContactTypeEnum.EMAIL.getValue());

			int startSmsHour = Integer.valueOf(map.get(
					ConstantsIdentifierEnum.START_SMS_HOUR.getValue())
					.getValue());
			int endSmsHour = Integer
					.valueOf(map.get(
							ConstantsIdentifierEnum.END_SMS_HOUR.getValue())
							.getValue());
			Calendar c = Calendar.getInstance();
			String actualHourString = String.valueOf(c
					.get(Calendar.HOUR_OF_DAY))
					+ String.valueOf(c.get(Calendar.MINUTE));
			int actualHour = Integer.valueOf(actualHourString);
			logger.debug("SMS =aplica???");
			if (startSmsHour <= actualHour && actualHour < endSmsHour) {
				logger.debug("SMS si aplica");
				values.add(CampaignContactTypeEnum.SMS.getValue());
			}

			criteria.andTipoIn(values);
			List<CpCampania> selectByExample = campaniaMapper
					.selectByExample(example);
			logger.debug("selectByExample.size() >>>" + selectByExample.size());

			for (CpCampania cpCampania : selectByExample) {

				if (cpCampania.getTipo().equals(
						CampaignContactTypeEnum.EMAIL.getValue())) {
					Gson gson = new Gson();
					Type object = new TypeToken<TreeMap<Integer, JamesDataDTO>>() {
					}.getType();
					TreeMap<Integer, JamesDataDTO> james = gson.fromJson(
							map.get(ConstantsIdentifierEnum.JAMES_AVIALABLE
									.getValue()).getValue(), object);

					logger.debug("james.size()" + james.size());
					logger.debug("campaignDTO.getLastJames()"
							+ cpCampania.getJamesAnterior());
					if (cpCampania.getJamesAnterior() != null
							&& cpCampania.getJamesAnterior() > 0
							&& cpCampania.getJamesAnterior() != james.get(
									james.size()).getId()) {
						cpCampania.setJamesAnterior(cpCampania
								.getJamesAnterior() + 1);
					} else {
						cpCampania.setJamesAnterior(james.get(1).getId());
					}
					this.campaniaMapper.updateByPrimaryKey(cpCampania);
					logger.debug("campaignDTO.getLastJames()after ="
							+ cpCampania.getJamesAnterior());
				}
				CampaignDTO campaignDTO = CAMPAIGNS_TRANSLATOR
						.reverseTranslateObject(cpCampania);

				Integer qtyMails = Integer.valueOf(map.get(
						ConstantsIdentifierEnum.QTY_MAILS_TO_SEND.getValue())
						.getValue());
				List<CpCliente> clients = clienteCustomMapper
						.getPendingClientsByContacted(
								cpCampania.getCampaniaId(), qtyMails);
				for (CpCliente cpCliente : clients) {

					if (cpCampania.getNoCia() != null
							&& !cpCampania.getNoCia().trim().isEmpty()) {
						campaignDTO.setStore(parametrizationService
								.getStoreById(cpCampania.getNoCia(),
										cpCampania.getCentro(), null));
					}
					if (cpCampania.getUnidadNegocio() != null
							&& !cpCampania.getUnidadNegocio().trim().isEmpty()) {
						campaignDTO.setBusinessUnit(parametrizationService
								.getBusinessUnitById(
										cpCampania.getUnidadNegocio(),
										cpCampania.getNoCiaUnidNeg(), null));
					}
					Integer id = sequencesCustomMapper
							.getTracingMessageSequence();
					TracingMessageDTO tracingMessage = new TracingMessageDTO();
					tracingMessage.setTracingMessageId(id);

					tracingMessage.setCampaign(campaignDTO);
					tracingMessage.setDateLastStatus(Calendar.getInstance()
							.getTime());
					tracingMessage.setClient(CLIENT_CAMPAIGN_TRANSLATOR_UTIL
							.reverseTranslateObject(cpCliente));
 
					if ((tracingMessage.getCampaign().getContactType()
							.equals(CampaignContactTypeEnum.EMAIL) && ValidatorUtil
							.validateEmailFormat(cpCliente.getCorreo()))
							|| (tracingMessage.getCampaign().getContactType()
									.equals(CampaignContactTypeEnum.SMS))) {
						tracingMessage
								.setTracingMessageStatus(TracingMessageStatusEnum.MSJ_SENT);
						tracingService.createTracingMessage(tracingMessage);
						jmsMessageProducer.generateMessages(tracingMessage);
					} else {
						tracingMessage
								.setTracingMessageStatus(TracingMessageStatusEnum.MSJ_INVALID_EMAIL);
						tracingService.createTracingMessage(tracingMessage);
					}
				}
			}
			audit.setStatus(OperationStatusEnum.OK);
			// audit.setEntityId(campaign.getCampaignId());

		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error startCampaign method ", e);
			throw new BusinessException(e,
					ErrorCodesEnum.GENERIC_TECHNICAL_EXCEPTION);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending startCampaign method");
		}
	}

	@Override
	public List<CampaignDTO> getCampaignsByUser(UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring getCampaignsByUser method");
		AuditDTO audit = AuditUtil.buildBasicAudit(
				AuditEventTypeEnum.GET_CAMPAIGNS, userInfo);
		try {
			List<CpCampania> list = campaniaCustomMapper
					.selectByStateAndTypeAndUser(
							CampaignStatusEnum.APPROVED.getValue(),
							CampaignContactTypeEnum.PHONE.getValue(),
							userInfo.getUserCode());
			audit.setStatus(OperationStatusEnum.OK);
			return CAMPAIGNS_TRANSLATOR.reverseTranslateObjectList(list);
		} catch (Exception e) {
			audit.setStatus(OperationStatusEnum.ERROR);
			logger.error("Error getCampaignsByUser method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getCampaignsByUser method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void endCampaigns() throws BusinessException {
		logger.debug("Staring endCampaigns method");

		try {
			CpCampaniaExample example = new CpCampaniaExample();
			Criteria criteria = example.createCriteria();
			criteria.andFinCampaniaLessThan(Calendar.getInstance().getTime());
			criteria.andEstadoEqualTo(CampaignStatusEnum.APPROVED.getValue());
			List<CpCampania> selectByExample = campaniaMapper
					.selectByExample(example);
			for (CpCampania cpCampania : selectByExample) {

				cpCampania.setEstado(CampaignStatusEnum.FINISHED.getValue());
				campaniaMapper.updateByPrimaryKey(cpCampania);
				AuditDTO audit = AuditUtil.buildBasicAudit(
						AuditEventTypeEnum.END_CAMPAIGN, null);
				audit.setStatus(OperationStatusEnum.OK);
				audit.setEntityId(cpCampania.getCampaniaId());
				auditService.registerAudit(audit);
			}

		} catch (Exception e) {
			logger.error("Error endCampaigns method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			logger.debug("Ending endCampaigns" + " method");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void closeCampaigns() throws BusinessException {
		logger.debug("Staring closeCampaigns method");

		try {
			CpCampaniaExample example = new CpCampaniaExample();
			Criteria criteria = example.createCriteria();
			criteria.andFinCampaniaLessThan(Calendar.getInstance().getTime());
			criteria.andEstadoEqualTo(CampaignStatusEnum.FINISHED.getValue());
			List<CpCampania> selectByExample = campaniaMapper
					.selectByExample(example);
			for (CpCampania cpCampania : selectByExample) {

				cpCampania.setEstado(CampaignStatusEnum.CLOSED.getValue());
				campaniaMapper.updateByPrimaryKey(cpCampania);
				sendReportByMail(cpCampania);
				AuditDTO audit = AuditUtil.buildBasicAudit(
						AuditEventTypeEnum.CLOSE_CAMPAIGN, null);
				audit.setStatus(OperationStatusEnum.OK);
				audit.setEntityId(cpCampania.getCampaniaId());
				auditService.registerAudit(audit);
			}

		} catch (Exception e) {
			logger.error("Error closeCampaigns method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			logger.debug("Ending closeCampaigns" + " method");
		}
	}
	
	private void sendReportByMail (CpCampania cpCampania) throws BusinessException {
		PdfReportGenerator pdfGenerator = new PdfReportGenerator(
				reportService, parametrizationService);
		SendEmail sendMail = new SendEmail();
		HashMap<String, ConstantDTO> allConstants = constantService
				.getAllConstants();
		String mailServerIP = allConstants.get(
				ConstantsIdentifierEnum.MAIL_SERVER_IP.getValue())
				.getValue();
		String mailServerPort = allConstants.get(
				ConstantsIdentifierEnum.MAIL_SERVER_PORT.getValue())
				.getValue();
	
		if (cpCampania.getTipo().equals(
				CampaignContactTypeEnum.EMAIL.getValue())
				|| cpCampania.getTipo().equals(
						CampaignContactTypeEnum.SMS.getValue())) {
			CustomMessageHeaderReportDTO customMessageHeaderReportDTO = reportService
					.getMessageHeaderReportByCampaignId(
							cpCampania.getCampaniaId(),
							CampaignContactTypeEnum.EMAIL.getValue(),
							null);
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList = reportService
					.getMessageHeaderReportEffectiveByCampaignId(
							cpCampania.getCampaniaId(),
							CampaignContactTypeEnum.EMAIL.getValue(),
							null);

			Integer businessUnitId = null;
			if (cpCampania.getUnidadNegocio() != null)
				businessUnitId = Integer.parseInt(cpCampania
						.getUnidadNegocio());

			List<CustomMessageClicksReportDTO> customMessageClicksReportList = null;
			if (cpCampania.getTipo().equals(
					CampaignContactTypeEnum.EMAIL.getValue())) {
				customMessageClicksReportList = reportService
						.getClicksByCampaignId(
								cpCampania.getCampaniaId(), null);
			}

			List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO = reportService
					.getEffectivenessByCampaignIdAndBusinessId(
							cpCampania.getCampaniaId(), businessUnitId,
							cpCampania.getInicioCampania(),
							cpCampania.getFinCampania());

			String pdfname = pdfGenerator
					.generateMessagesCampaignReport(
							customMessageHeaderReportDTO,
							customPhoneHeaderReportEffectiveCallsDTOList,
							customMessageClicksReportList,
							customMessageEffectivenessReportDTO);
			logger.info("closeCampaigns method =====> pdfname "
					+ pdfname);
			if (pdfname != null) {
				List<String> attachments = new ArrayList<String>();
				attachments.add(pdfname);
				// Approver
				String emailTo = "";
				PdUsuario creator = pdUsuarioMapper
						.selectByPrimaryKey(customMessageHeaderReportDTO
								.getUserCreator());
				if (creator != null && creator.getTxCorreoUsu() != null)
					emailTo += creator.getTxCorreoUsu();

				if (customMessageHeaderReportDTO.getUserCreator()
						.compareTo(
								customMessageHeaderReportDTO
										.getApprover()) != 0) {
					PdUsuario approver = pdUsuarioMapper
							.selectByPrimaryKey(customMessageHeaderReportDTO
									.getApprover());
					if (approver != null
							&& approver.getTxCorreoUsu() != null)
						emailTo += "," + approver.getTxCorreoUsu();
				}
				// TODO send mail with report attachment
				if (emailTo.length() > 0) {
					logger.info("closeCampaigns method =====> sendingMail "
							+ emailTo);
					sendMail.send(
							customMessageHeaderReportDTO.getDomain(), emailTo,
							"Reporte Campaña: "
									+ customMessageHeaderReportDTO
											.getName(), "Este es el resumen de los resultados de la campaña",
							mailServerIP, mailServerPort, attachments);
					logger.info("closeCampaigns method =====> sendingMail Sent to "
							+ emailTo);
				}

			}
		} else {
			CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO = reportService
					.getPhoneHeaderReportByCampaignId(
							cpCampania.getCampaniaId(),
							null);
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList = reportService
					.getMessageHeaderReportEffectiveByCampaignId(
							cpCampania.getCampaniaId(),
							CampaignContactTypeEnum.EMAIL.getValue(),
							null);

			Integer businessUnitId = null;
			if (cpCampania.getUnidadNegocio() != null)
				businessUnitId = Integer.parseInt(cpCampania
						.getUnidadNegocio());


			List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO = reportService
					.getEffectivenessByCampaignIdAndBusinessId(
							cpCampania.getCampaniaId(), businessUnitId,
							cpCampania.getInicioCampania(),
							cpCampania.getFinCampania());

			String pdfname = pdfGenerator
					.generatePhoneCampaignReport(
							customPhoneHeaderReportDTO,
							customPhoneHeaderReportEffectiveCallsDTOList,
							customMessageEffectivenessReportDTO);
			logger.info("closeCampaigns method =====> pdfname "
					+ pdfname);
			if (pdfname != null) {
				List<String> attachments = new ArrayList<String>();
				attachments.add(pdfname);
				// Approver
				String emailTo = "";
				PdUsuario creator = pdUsuarioMapper
						.selectByPrimaryKey(customPhoneHeaderReportDTO
								.getUserCreator());
				if (creator != null && creator.getTxCorreoUsu() != null)
					emailTo += creator.getTxCorreoUsu();

				if (customPhoneHeaderReportDTO.getUserCreator()
						.compareTo(
								customPhoneHeaderReportDTO
										.getApprover()) != 0) {
					PdUsuario approver = pdUsuarioMapper
							.selectByPrimaryKey(customPhoneHeaderReportDTO
									.getApprover());
					if (approver != null
							&& approver.getTxCorreoUsu() != null)
						emailTo += "," + approver.getTxCorreoUsu();
				}
				// TODO send mail with report attachment
				if (emailTo.length() > 0) {
					logger.info("closeCampaigns method =====> sendingMail "
							+ emailTo);
					sendMail.send(
							customPhoneHeaderReportDTO.getDomain(), emailTo
							,
							"Reporte Campaña: "
									+ customPhoneHeaderReportDTO
											.getName(), "Este es el resumen de los resultados de la campaña",
							mailServerIP, mailServerPort, attachments);
					logger.info("closeCampaigns method =====> sendingMail Sent to "
							+ emailTo);
				}

			}

		}
	}
}
