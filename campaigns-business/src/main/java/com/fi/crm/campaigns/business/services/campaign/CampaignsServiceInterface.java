package com.fi.crm.campaigns.business.services.campaign;

import java.util.List;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;

public interface CampaignsServiceInterface {

	public CampaignDTO getCampaignById(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;

	public List<CampaignDTO> getCampaignsByStateAndDate(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;

	public CampaignDTO createCampaign(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;

	public CampaignDTO updateCampaign(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;

	public void deleteCampaign(CampaignDTO campaign, UserInfoDTO userInfo) throws BusinessException;

	public void startCampaign() throws BusinessException;

	public List<CampaignDTO> getCampaignsByUser(UserInfoDTO userInfo) throws BusinessException;

	public void endCampaigns() throws BusinessException;

	public void closeCampaigns() throws BusinessException;
}
