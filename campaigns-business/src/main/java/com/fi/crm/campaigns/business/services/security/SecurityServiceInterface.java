package com.fi.crm.campaigns.business.services.security;

import com.fi.crm.campaigns.common.dto.LoginCredentialsDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;


public interface SecurityServiceInterface {
	
	public UserInfoDTO login(LoginCredentialsDTO credentials) throws BusinessException;
	public String encryptPassword(String password) throws BusinessException;

}
