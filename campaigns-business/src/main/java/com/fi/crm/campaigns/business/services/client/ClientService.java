package com.fi.crm.campaigns.business.services.client;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.PvclientesCustomMapper;
import com.fi.crm.campaigns.persistence.mappers.PvclientesMapper;
import com.fi.crm.campaigns.persistence.model.Pvclientes;
import com.fi.crm.campaigns.persistence.model.PvclientesExample;
import com.fi.crm.campaigns.persistence.model.PvclientesExample.Criteria;
import com.fi.crm.campaigns.persistence.util.ClientTranslatorUtil;

public class ClientService implements ClientServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private PvclientesMapper clientMapper;
	@Autowired
	private PvclientesCustomMapper clientsCustomMapper;
	@Autowired
	private ConstantServiceInterface constantService;

	//translator
	private static final ClientTranslatorUtil CLIENT_TRANSLATOR_UTIL = new ClientTranslatorUtil();

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ClientCampaignDTO createOrUpdateClient(ClientCampaignDTO client) throws BusinessException {
		logger.debug("Starting createOrUpdateClient");
		try {
			Pvclientes pvcliente = CLIENT_TRANSLATOR_UTIL.translateObject(client);

			pvcliente.setIndActivo("S");
			if (client.getCountry() == null){
				pvcliente.setPais("169"); //Colombia
				pvcliente.setPaisNacionalidad("079"); //Colombiano
			}
			
			if ( "169".equals(client.getCountry()) ) {
				pvcliente.setIndNacional("S");
			} else {
				pvcliente.setIndNacional("N");
			}
			pvcliente.setIndCodCliente("R");
			pvcliente.setTstamp(Calendar.getInstance().getTime());
			pvcliente.setFhCrea(Calendar.getInstance().getTime()); //Created rigth now
			pvcliente.setNoTarjeta(client.getDocumentNumber()); //Set the document number
			pvcliente.setTelefono(client.getCellPhone());
			pvcliente.setTelefonoOficina(client.getTelephone());
			//New client
			if ( client.getClientCode() == null ) {
				CampaignDTO campaignDTO = client.getCampaign();
				StoreDTO storeDTO = campaignDTO.getStore();
				String clientCode = clientsCustomMapper.getClientCode(storeDTO.getNoCia(), 
						storeDTO.getCenter(), "R");
				pvcliente.setCodCliente(clientCode);
				pvcliente.setNoCia(storeDTO.getNoCia());
				pvcliente.setCentrodCrea(storeDTO.getCenter());
				pvcliente.setIndEmpleado("N");
				client.setClientCode(clientCode);
				clientMapper.insert(pvcliente);

			} else { //Update client
				pvcliente.setCodCliente(client.getClientCode());
				clientMapper.updateByPrimaryKeySelective(pvcliente);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("Error creating or updating client", ErrorCodesEnum.GENERIC_BUSINESS_EXCEPTION);
		} finally {
			logger.debug("Ending createOrUpdateClient");
		}
		return client;
	}

	@Override
	public ClientCampaignDTO getClientByDocumentTypeAndDocument(ClientCampaignDTO clientCampaign) throws BusinessException{
		logger.debug("Starting getClientByDocumentTypeAndDocument");
		try {

			PvclientesExample example = new PvclientesExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdIdentificacionEqualTo(clientCampaign.getDocumentNumber());
			criteria.andTipoIdentificacionEqualTo(clientCampaign.getDocumentType().getId());
			List<Pvclientes> list = clientMapper.selectByExample(example);
			if(list != null && !list.isEmpty()){
				return CLIENT_TRANSLATOR_UTIL.reverseTranslateObject(list.get(0));
			}
			return null;

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("Error getClientByDocumentTypeAndDocument", ErrorCodesEnum.GENERIC_BUSINESS_EXCEPTION);
		} finally {
			logger.debug("Ending getClientByDocumentTypeAndDocument");
		}
	}

}
