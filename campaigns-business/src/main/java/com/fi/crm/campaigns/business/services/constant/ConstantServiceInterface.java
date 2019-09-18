package com.fi.crm.campaigns.business.services.constant;

import java.util.HashMap;

import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;



public interface ConstantServiceInterface {
	
	public HashMap<String, ConstantDTO> getAllConstants() throws BusinessException;
	

}
