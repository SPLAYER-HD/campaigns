package com.fi.crm.campaigns.business.services.report;

import java.util.Date;
import java.util.List;

import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageDetailReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneDetailReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;

public interface ReportServiceInterface {
	CustomPhoneHeaderReportDTO getPhoneHeaderReportByCampaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException;

	List<CustomPhoneHeaderReportEffectiveCallsDTO> getPhoneHeaderReportEffectiveCallsByCampaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException;

	List<CustomPhoneDetailReportDTO> getPhoneDetailReportByCamapaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException;

	CustomMessageHeaderReportDTO getMessageHeaderReportByCampaignId(
			Integer campaignId, String messageType, UserInfoDTO userInfo)
			throws BusinessException;

	List<CustomPhoneHeaderReportEffectiveCallsDTO> getMessageHeaderReportEffectiveByCampaignId(
			Integer campaignId, String messageType, UserInfoDTO userInfo)
			throws BusinessException;

	List<CustomMessageDetailReportDTO> getMessageDetailedReportByCampaingId(
			Integer campaignId, String messageType, UserInfoDTO userInfo)
			throws BusinessException;

	List<CustomMessageClicksReportDTO> getClicksByCampaignId(
			Integer campaignId, UserInfoDTO userInfo) throws BusinessException;

	List<CustomMessageEffectivenessReportDTO> getEffectivenessByCampaignIdAndBusinessId(
			Integer campaignId, Integer businessId, Date startDate, Date endDate)
			throws BusinessException;
}
