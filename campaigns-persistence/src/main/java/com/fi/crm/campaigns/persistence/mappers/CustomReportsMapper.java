package com.fi.crm.campaigns.persistence.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fi.crm.campaigns.persistence.model.CustomMessageClicksReport;
import com.fi.crm.campaigns.persistence.model.CustomMessageDetailReport;
import com.fi.crm.campaigns.persistence.model.CustomMessageEffectivenessReport;
import com.fi.crm.campaigns.persistence.model.CustomMessageHeaderReport;
import com.fi.crm.campaigns.persistence.model.CustomPhoneDetailReport;
import com.fi.crm.campaigns.persistence.model.CustomPhoneHeaderReport;
import com.fi.crm.campaigns.persistence.model.CustomPhoneHeaderReportEffectiveCalls;

public interface CustomReportsMapper {

	CustomPhoneHeaderReport getPhoneHeaderReportByCampaignId(
			@Param("campaignId") Integer campaignId);

	List<CustomPhoneHeaderReportEffectiveCalls> getPhoneHeaderReportEffectiveCallsByCampaignId(
			@Param("campaignId") Integer campaignId);

	List<CustomPhoneDetailReport> getPhoneDetailReportByCamapaignId(
			@Param("campaignId") Integer campaignId);

	CustomMessageHeaderReport getMessageHeaderReportByCampaignId(
			@Param("campaignId") Integer campaignId,
			@Param("messageType") String messageType);

	List<CustomPhoneHeaderReportEffectiveCalls> getMessageHeaderReportEffectiveByCampaignId(
			@Param("campaignId") Integer campaignId,
			@Param("messageType") String messageType);

	List<CustomMessageDetailReport> getMessageDetailedReportByCampaingId(
			@Param("campaignId") Integer campaignId,
			@Param("messageType") String messageType);

	List<CustomMessageClicksReport> getClicksByCampaignId(
			@Param("campaignId") Integer campaignId);

	List<CustomMessageEffectivenessReport> getEffectivenessByCampaignIdAndBusinessId(
			@Param("campaignId") Integer campaignId,
			@Param("businessId") Integer businessId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}