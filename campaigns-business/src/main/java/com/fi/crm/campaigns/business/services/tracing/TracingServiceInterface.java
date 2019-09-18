package com.fi.crm.campaigns.business.services.tracing;

import java.util.List;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.TracingDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;


public interface TracingServiceInterface {
	
	public void updateTracing(TracingDTO tracing, UserInfoDTO userInfo) throws BusinessException;
	public TracingDTO getClientTracingByCampaign(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;
	public TracingDTO createTracing(TracingDTO tracing, UserInfoDTO userInfo) throws BusinessException;
	public TracingMessageDTO createTracingMessage(TracingMessageDTO tracingMessage) throws BusinessException;
	public TracingDTO getTracingByCampaign(CampaignDTO campaign, ClientCampaignDTO client, UserInfoDTO userInfo) throws BusinessException;
	public void freeCallsCustomer() throws BusinessException;
	public void onloadMailByClient(Integer tracingMessageId) throws BusinessException;
	public List<TracingDTO> getTracingByCampaignId(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;
	public List<TracingMessageDTO> getTracingMessageByCampaignId(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;
	public void updateTracingMessage(TracingMessageDTO tracing, UserInfoDTO userInfo) throws BusinessException;
}
