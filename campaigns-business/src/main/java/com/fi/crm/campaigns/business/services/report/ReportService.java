package com.fi.crm.campaigns.business.services.report;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageDetailReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneDetailReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.CustomReportsMapper;
import com.fi.crm.campaigns.persistence.model.CustomMessageClicksReport;
import com.fi.crm.campaigns.persistence.model.CustomMessageDetailReport;
import com.fi.crm.campaigns.persistence.model.CustomMessageEffectivenessReport;
import com.fi.crm.campaigns.persistence.model.CustomMessageHeaderReport;
import com.fi.crm.campaigns.persistence.model.CustomPhoneDetailReport;
import com.fi.crm.campaigns.persistence.model.CustomPhoneHeaderReport;
import com.fi.crm.campaigns.persistence.model.CustomPhoneHeaderReportEffectiveCalls;
import com.fi.crm.campaigns.persistence.util.CustomMessageClicksReportTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.CustomMessageDetailReportTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.CustomMessageEffectivenessReportTransalatorUtil;
import com.fi.crm.campaigns.persistence.util.CustomMessageHeaderReportTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.CustomPhoneDetailReportTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.CustomPhoneHeaderReportEffectiveCallsTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.CustomPhoneHeaderReportTranslatorUtil;
import com.google.gson.Gson;

public class ReportService implements ReportServiceInterface {

	private static final Logger logger = LoggerFactory
			.getLogger(ReportService.class);

	@Autowired
	private CustomReportsMapper customReportsMapper;

	private static final CustomPhoneHeaderReportTranslatorUtil CUSTOM_PHONE_HEADER_REPORT_TRANSLATOR = new CustomPhoneHeaderReportTranslatorUtil();
	private static final CustomPhoneHeaderReportEffectiveCallsTranslatorUtil CUSTOM_PHONE_HEADER_REPORT_EFFECTIVE_CALLS_TRANSLATOR = new CustomPhoneHeaderReportEffectiveCallsTranslatorUtil();
	private static final CustomPhoneDetailReportTranslatorUtil CUSTOM_PHONE_DETAIL_REPORT_TRANSLATOR = new CustomPhoneDetailReportTranslatorUtil();
	private static final CustomMessageHeaderReportTranslatorUtil CUSTOM_MESSAGE_HEADER_REPORT_TRANSLATOR = new CustomMessageHeaderReportTranslatorUtil();
	private static final CustomMessageDetailReportTranslatorUtil CUSTOM_MESSAGE_DETAIL_REPORT_TRANSLATOR = new CustomMessageDetailReportTranslatorUtil();
	private static final CustomMessageClicksReportTranslatorUtil CUSTOM_MESSAGE_CLICKS_REPORT_TRANSLATOR = new CustomMessageClicksReportTranslatorUtil();
	private static final CustomMessageEffectivenessReportTransalatorUtil CUSTOM_MESSAGE_EFFECTIVENESS_REPORT_TRANSLATOR = new CustomMessageEffectivenessReportTransalatorUtil();

	@Override
	public CustomPhoneHeaderReportDTO getPhoneHeaderReportByCampaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException {
		try {
			CustomPhoneHeaderReport customPhoneHeaderReportList = customReportsMapper
					.getPhoneHeaderReportByCampaignId(campaignId);
			CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO = CUSTOM_PHONE_HEADER_REPORT_TRANSLATOR
					.reverseTranslateObject(customPhoneHeaderReportList);

			return customPhoneHeaderReportDTO;
		} catch (Exception e) {
			logger.error("Error getPhoneHeaderReportByCampaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getPhoneHeaderReportByCampaignId method");
		}
	}

	@Override
	public List<CustomPhoneHeaderReportEffectiveCallsDTO> getPhoneHeaderReportEffectiveCallsByCampaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException {
		try {
			List<CustomPhoneHeaderReportEffectiveCalls> customPhoneHeaderReportList = customReportsMapper
					.getPhoneHeaderReportEffectiveCallsByCampaignId(campaignId);
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportDTOList = CUSTOM_PHONE_HEADER_REPORT_EFFECTIVE_CALLS_TRANSLATOR
					.reverseTranslateObjectList(customPhoneHeaderReportList);

			return customPhoneHeaderReportDTOList;
		} catch (Exception e) {
			logger.error(
					"Error getPhoneHeaderReportEffectiveCallsByCampaignId method ",
					e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getPhoneHeaderReportEffectiveCallsByCampaignId method");
		}
	}

	@Override
	public List<CustomPhoneDetailReportDTO> getPhoneDetailReportByCamapaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException {
		try {
			List<CustomPhoneDetailReport> customPhoneDetailReportList = customReportsMapper
					.getPhoneDetailReportByCamapaignId(campaignId);
			List<CustomPhoneDetailReportDTO> customPhoneDetailReportDTOList = CUSTOM_PHONE_DETAIL_REPORT_TRANSLATOR
					.reverseTranslateObjectList(customPhoneDetailReportList);

			return customPhoneDetailReportDTOList;
		} catch (Exception e) {
			logger.error("Error getPhoneDetailReportByCamapaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getPhoneDetailReportByCamapaignId method");
		}
	}

	@Override
	public CustomMessageHeaderReportDTO getMessageHeaderReportByCampaignId(
			Integer campaignId, String messageType, UserInfoDTO userInfo)
			throws BusinessException {
		try {
			CustomMessageHeaderReport customMessageHeaderReportList = customReportsMapper
					.getMessageHeaderReportByCampaignId(campaignId, messageType);
			Gson gson = new Gson();
			System.out.println(gson.toJson(customMessageHeaderReportList));
			CustomMessageHeaderReportDTO customMessageHeaderReportDTO = CUSTOM_MESSAGE_HEADER_REPORT_TRANSLATOR
					.reverseTranslateObject(customMessageHeaderReportList);
			System.out.println(gson.toJson(customMessageHeaderReportDTO));

			return customMessageHeaderReportDTO;
		} catch (Exception e) {
			logger.error("Error getMessageHeaderReportByCampaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getMessageHeaderReportByCampaignId method");
		}
	}

	@Override
	public List<CustomPhoneHeaderReportEffectiveCallsDTO> getMessageHeaderReportEffectiveByCampaignId(
			Integer campaignId, String messageType, UserInfoDTO userInfo)
			throws BusinessException {
		try {
			List<CustomPhoneHeaderReportEffectiveCalls> customPhoneHeaderReportList = customReportsMapper
					.getMessageHeaderReportEffectiveByCampaignId(campaignId,
							messageType);
			List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportDTOList = CUSTOM_PHONE_HEADER_REPORT_EFFECTIVE_CALLS_TRANSLATOR
					.reverseTranslateObjectList(customPhoneHeaderReportList);

			return customPhoneHeaderReportDTOList;
		} catch (Exception e) {
			logger.error(
					"Error getMessageHeaderReportEffectiveByCampaignId method ",
					e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getMessageHeaderReportEffectiveByCampaignId method");
		}
	}

	@Override
	public List<CustomMessageDetailReportDTO> getMessageDetailedReportByCampaingId(
			Integer campaignId, String messageType, UserInfoDTO userInfo)
			throws BusinessException {
		try {
			List<CustomMessageDetailReport> customPhoneDetailReportList = customReportsMapper
					.getMessageDetailedReportByCampaingId(campaignId,
							messageType);
			List<CustomMessageDetailReportDTO> customPhoneDetailReportDTOList = CUSTOM_MESSAGE_DETAIL_REPORT_TRANSLATOR
					.reverseTranslateObjectList(customPhoneDetailReportList);

			return customPhoneDetailReportDTOList;
		} catch (Exception e) {
			logger.error("Error getPhoneDetailReportByCamapaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getPhoneDetailReportByCamapaignId method");
		}
	}

	@Override
	public List<CustomMessageClicksReportDTO> getClicksByCampaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException {
		try {
			List<CustomMessageClicksReport> customMessageClicksReportList = customReportsMapper
					.getClicksByCampaignId(campaignId);
			List<CustomMessageClicksReportDTO> customMessageClicksReportDTOList = CUSTOM_MESSAGE_CLICKS_REPORT_TRANSLATOR
					.reverseTranslateObjectList(customMessageClicksReportList);

			return customMessageClicksReportDTOList;
		} catch (Exception e) {
			logger.error("Error getClicksByCampaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getClicksByCampaignId method");
		}
	}

	@Override
	public List<CustomMessageEffectivenessReportDTO> getEffectivenessByCampaignIdAndBusinessId(
			Integer campaignId, Integer businessId, Date startDate, Date endDate) 
			throws BusinessException {
		try {
			List<CustomMessageEffectivenessReport> customMessageEffectivenessReportList = customReportsMapper
					.getEffectivenessByCampaignIdAndBusinessId(campaignId, businessId, startDate, endDate);
			List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTOList = CUSTOM_MESSAGE_EFFECTIVENESS_REPORT_TRANSLATOR
					.reverseTranslateObjectList(customMessageEffectivenessReportList);

			return customMessageEffectivenessReportDTOList;
		} catch (Exception e) {
			logger.error("Error getClicksByCampaignId method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getClicksByCampaignId method");
		}
	}

}
