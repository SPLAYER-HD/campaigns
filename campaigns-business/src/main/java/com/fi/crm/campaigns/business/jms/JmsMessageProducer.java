package com.fi.crm.campaigns.business.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.fi.crm.campaigns.common.dto.TracingMessageDTO;

@Component
public class JmsMessageProducer implements JmsMessageProducerInterface{


	/**
	 * static logger for class JmsMessageProducer
	 */
	private static final Logger logger = LoggerFactory.getLogger(JmsMessageProducer.class);
    protected static final String MESSAGE_TRACING_MESSAGE = "MESSAGE_TRACING_MESSAGE";

    @Autowired
    private JmsTemplate jmsProducerTemplate;

    /**
     * Generates JMS messages
     */
    public void generateMessages(final TracingMessageDTO tracingMessage) throws JMSException {
		logger.debug("Staring generateMessages method");
		try {
		
           jmsProducerTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    Message message = session.createObjectMessage(tracingMessage);
//                    message.setObjectProperty(MESSAGE_TRACING_MESSAGE, tracingMessage);
                    
                    logger.info("Sending message tracingMessage id : " + tracingMessage.getTracingMessageId());
                    
                    return message;
                }
            });
	        
		} catch (Exception e) {
			logger.error("Error generateMessages method ", e);
		} finally {
			logger.debug("Ending generateMessages method");
		}
    }

}
       