package com.fi.crm.campaigns.business.services.constant;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.CpConstanteMapper;
import com.fi.crm.campaigns.persistence.model.CpConstante;
import com.fi.crm.campaigns.persistence.model.CpConstanteExample;
import com.fi.crm.campaigns.persistence.util.ConstantTranslatorUtil;
import com.googlecode.ehcache.annotations.Cacheable;

public class ConstantService implements ConstantServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory.getLogger(ConstantService.class);

	// Translators
	private static final ConstantTranslatorUtil CONSTANT_TRANSLATOR_UTIL = new ConstantTranslatorUtil();

	@Autowired
	private CpConstanteMapper constanteMapper;

	@Override
	@Cacheable(cacheName = "getAllConstants")
	public HashMap<String, ConstantDTO> getAllConstants() throws BusinessException {
		logger.debug("Staring getCampaignById method");
		try {
			CpConstanteExample example = new CpConstanteExample();
			example.createCriteria().getAllCriteria();
			List<CpConstante> list = constanteMapper.selectByExample(example);
			HashMap<String, ConstantDTO> map = new HashMap<>();
			for (CpConstante constante : list) {
				map.put(constante.getConstanteId(), CONSTANT_TRANSLATOR_UTIL.reverseTranslateObject(constante));
			}
			return map;
			
		} catch (Exception e) {
			logger.error("Error getCampaignById method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getCampaignById method");
		}
	}

}
