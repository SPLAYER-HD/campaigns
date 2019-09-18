/**
 * 
 */
package com.fi.crm.campaigns.common.mail;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date feb, 10/2015
 */
public class MailUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

	/**
	 * @param body
	 * @param templatePath
	 * @param servletIP
	 * @return String
	 */
	public static String loadTemplate(String body, String templatePath, String templateName, String servletIP){

		try{

			VelocityContext context = new VelocityContext();
			context.put("content", body);
			context.put("servletIp", servletIP);

			/* first, get and initialize an engine */
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templatePath);
			ve.init();
			/* get the Template */
			Template t = ve.getTemplate(templateName);
			/* now render the template into a Writer */
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			/* use the output in your email body */
			return writer.toString();

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Format email message body
	 * @param campaign
	 * @param message
	 * @return String
	 */
	public static String loadMailBody(TracingMessageDTO tracingMessageDTO, String message){

		try{

			CampaignDTO campaign = tracingMessageDTO.getCampaign();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			message = message.replaceAll("#name", campaign.getName());
			message = message.replaceAll("#desc", campaign.getDescription());
			message = message.replaceAll("#strdate", dateFormat.format(campaign.getStartDate()));
			message = message.replaceAll("#enddate", dateFormat.format(campaign.getEndDate()));
			message = message.replaceAll("#strevent", dateFormat.format(campaign.getEventStartDate()));
			message = message.replaceAll("#endevent", dateFormat.format(campaign.getEventEndDate()));
			message = message.replaceAll("#bissunit", (campaign.getBusinessUnit() != null ? (campaign.getBusinessUnit().getName() != null ? campaign.getBusinessUnit().getName() : "") : ""));
			message = message.replaceAll("#store", (campaign.getStore() != null ? (campaign.getStore().getName() != null ? campaign.getStore().getName() : "") : ""));

			ClientCampaignDTO client = tracingMessageDTO.getClient();

			if (client == null)
				return message;

			message = message.replaceAll("#clientName", client.getFirstName() != null ? client.getFirstName() : "");
			message = message.replaceAll("#clientLastName1", (client.getLastName1() != null ? client.getLastName1() : ""));
			message = message.replaceAll("#clientLastName2", (client.getLastName2() != null ? client.getLastName2() : ""));
			message = message.replaceAll("#extraStr", (client.getExtraStr() != null ? client.getExtraStr() : ""));
			message = message.replaceAll("#extraNum", (client.getExtraNum() != null ? client.getExtraNum().toString() : ""));
			message = message.replaceAll("#extraDate", (campaign.getEndDate() != null ? dateFormat.format(campaign.getEndDate()) : ""));

			return message;

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}