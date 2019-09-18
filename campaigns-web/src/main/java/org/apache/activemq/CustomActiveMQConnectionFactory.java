package org.apache.activemq;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;

public class CustomActiveMQConnectionFactory extends ActiveMQConnectionFactory{

	
	/**
	 * static logger for class CustomActiveMQConnectionFactory
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustomActiveMQConnectionFactory.class);
	public CustomActiveMQConnectionFactory(ConstantServiceInterface constantService){
		logger.debug("Staring CustomActiveMQConnectionFactory method");
		try {
			
			HashMap<String, ConstantDTO> allConstants = constantService.getAllConstants();
			setBrokerURL(allConstants.get(ConstantsIdentifierEnum.ACTIVE_MQ_SERVER_URL.getValue()).getValue());			
			
		} catch (Exception e) {
			logger.error("Error CustomActiveMQConnectionFactory method ", e);
		} finally {
			
			logger.debug("Ending CustomActiveMQConnectionFactory method");
		}
	}
	
}
