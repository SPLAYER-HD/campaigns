package com.fi.crm.campaigns.business.services.client;

import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;



public interface ClientServiceInterface {
	
	public ClientCampaignDTO createOrUpdateClient(ClientCampaignDTO clientDTO) throws BusinessException;
	public ClientCampaignDTO getClientByDocumentTypeAndDocument(ClientCampaignDTO clientCampaign) throws BusinessException;
}
