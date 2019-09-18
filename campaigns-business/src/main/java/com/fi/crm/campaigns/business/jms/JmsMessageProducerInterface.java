package com.fi.crm.campaigns.business.jms;

import javax.jms.JMSException;

import com.fi.crm.campaigns.common.dto.TracingMessageDTO;

public interface JmsMessageProducerInterface {
	
	 public void generateMessages(TracingMessageDTO tracingMessage) throws JMSException;

}
