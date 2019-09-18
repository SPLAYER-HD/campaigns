package com.fi.crm.campaigns.business.jms;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.TreeMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.business.services.tracing.TracingServiceInterface;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.dto.JamesDataDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.common.enums.TracingMessageStatusEnum;
import com.fi.crm.campaigns.common.mail.MailUtil;
import com.fi.crm.campaigns.infrastructure.mail.SendEmail;
import com.fi.crm.campaigns.infrastructure.sms.SendSMS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JmsMessageListener implements MessageListener { 

    
	/**
	 * static logger for class JmsMessageListener
	 */
	private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

	public static final String MAIL_TEMPLATE_NAME = "mailTemplate.vm";
	

    @Autowired
    private ConstantServiceInterface constantService;
    @Autowired
	private JmsMessageProducerInterface jmsMessageProducer;
    @Autowired
	private TracingServiceInterface tracingService;

    /**
     * Implementation of <code>MessageListener</code>.
     */
    public void onMessage(Message message) {
        logger.debug("Staring onMessage method");
		try {
			ActiveMQObjectMessage msg = (ActiveMQObjectMessage) message;
            TracingMessageDTO tracingMessage = (TracingMessageDTO) msg.getObject();
//            TracingMessageDTO tracingMessage = (TracingMessageDTO) message.getObjectProperty(JmsMessageProducer.MESSAGE_TRACING_MESSAGE);
        	HashMap<String, ConstantDTO> allConstants = constantService.getAllConstants();
        	if(tracingMessage.getCampaign().getContactType().equals(CampaignContactTypeEnum.EMAIL)){
				
        		Gson gson = new Gson();
				Type object = new  TypeToken<TreeMap<Integer, JamesDataDTO>>(){}.getType();
				TreeMap<Integer, JamesDataDTO> jamesTree = gson.fromJson(allConstants.get(ConstantsIdentifierEnum.JAMES_AVIALABLE.getValue()).getValue(), object);			
				JamesDataDTO james = jamesTree.get(tracingMessage.getCampaign().getLastJames());
        		
//				String mailServerIP = allConstants.get(ConstantsIdentifierEnum.MAIL_SERVER_IP.getValue()).getValue();
//				String mailServerPort = allConstants.get(ConstantsIdentifierEnum.MAIL_SERVER_PORT.getValue()).getValue();
				String mailServerIP = james.getIp();
				String mailServerPort = james.getPort();
				logger.debug("mailServerIP"+mailServerIP);
				logger.debug("mailServerPort"+mailServerPort);
				String templatePath = allConstants.get(ConstantsIdentifierEnum.MAIL_TEMPLATE_PATH.getValue()).getValue();
				String sender = tracingMessage.getCampaign().getDomain();
				String servletIP = allConstants.get(ConstantsIdentifierEnum.MAIL_SERVLET_URL.getValue()).getValue();
				servletIP += "?tracingMessageId=" + tracingMessage.getTracingMessageId();
				String mailBody = MailUtil.loadMailBody(tracingMessage, tracingMessage.getCampaign().getMessage());
				mailBody = MailUtil.loadTemplate(mailBody, templatePath, MAIL_TEMPLATE_NAME, servletIP);
				SendEmail sendEmail = new SendEmail();
				sendEmail.send(sender, tracingMessage.getClient().getEmail(), tracingMessage.getCampaign().getMailSubject(), mailBody, mailServerIP, mailServerPort, null);
//				sendEmail.send(sender, tracingMessage.getClient().getEmail(), tracingMessage.getCampaign().getMailSubject()+" ID="+tracingMessage.getClient().getClientId(), mailBody, mailServerIP, mailServerPort);
				
			}else if(tracingMessage.getCampaign().getContactType().equals(CampaignContactTypeEnum.SMS)){
				
				String user = allConstants.get(ConstantsIdentifierEnum.SMS_ITCLOUD_USER.getValue()).getValue();
				String password = allConstants.get(ConstantsIdentifierEnum.SMS_ITCLOUD_PASSWORD.getValue()).getValue();
				String phoneInd = allConstants.get(ConstantsIdentifierEnum.SMS_INDICATIVE.getValue()).getValue();
				String smsUrl = allConstants.get(ConstantsIdentifierEnum.SMS_URL.getValue()).getValue();
				String response = SendSMS.sendSMSITCloud(user, password, phoneInd, smsUrl, tracingMessage.getClient().getCellPhone(),tracingMessage.getCampaign().getMessage());
				logger.debug("##response##"+response);
				if(response.equals(TracingMessageStatusEnum.FAIL_AUTENTICATION.getValue()) || 
						response.equals(TracingMessageStatusEnum.OUTSIDE_AUTHORIZED_HOURS.getValue()) || 
						response.equals(TracingMessageStatusEnum.BALANCE_ERROR.getValue()) || 
						//response.equals(TracingMessageStatusEnum.MESSAGE_ERROR.getValue()) || 
						response.equals(TracingMessageStatusEnum.SYSTEM_IN_MAINTENANCE.getValue())){
					jmsMessageProducer.generateMessages(tracingMessage);
				}
				if(response.equals(TracingMessageStatusEnum.FAIL_AUTENTICATION.getValue()) ||
						response.equals(TracingMessageStatusEnum.OUTSIDE_AUTHORIZED_HOURS.getValue()) ||
						response.equals(TracingMessageStatusEnum.BALANCE_ERROR.getValue()) ||
						response.equals(TracingMessageStatusEnum.MOBILE_FORMAT_ERROR.getValue()) ||
						response.equals(TracingMessageStatusEnum.MESSAGE_ERROR.getValue()) ||
						response.equals(TracingMessageStatusEnum.SYSTEM_IN_MAINTENANCE.getValue()) ||
						response.equals(TracingMessageStatusEnum.QUANTITY_MAX_MOBILE.getValue()) ||
						response.equals(TracingMessageStatusEnum.OPERATOR_NOT_EXISTS.getValue())){
					
					tracingMessage.setTracingMessageStatus(TracingMessageStatusEnum.byValue(response));
					tracingService.updateTracingMessage(tracingMessage, null);
				}
			}
            
        } catch (JMSException e) {
            logger.error("Error onMessage: JMSException", e);
		} catch (Exception e) {
			logger.error("Error onMessage method ", e);
		} finally {
			logger.debug("Ending onMessage method");
		}
    }
    
}