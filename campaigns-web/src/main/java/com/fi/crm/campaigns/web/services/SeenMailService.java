package com.fi.crm.campaigns.web.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fi.crm.campaigns.business.services.tracing.TracingServiceInterface;

@Controller
public class SeenMailService {

	/**
	 * static logger for class OnloadMailServlet
	 */
	private static final Logger logger = LoggerFactory.getLogger(SeenMailService.class);

	@Autowired
	private TracingServiceInterface tracingService;

	// CONSTANTES ESTADO DE LA TRANSACCION
	private static final String STATUS_OK = "OK";
	private static final String STATUS_ERROR = "ERROR";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeenMailService() {
		super();
		System.out.println("SeenMailService");
	}

	@RequestMapping(value = "/SeenMailService", method = { RequestMethod.GET })
	protected ResponseSeenMailServiceDTO markSeenMail(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Staring markSeenMail method");
		ResponseSeenMailServiceDTO responseSeenMailService = new ResponseSeenMailServiceDTO();
		try {
			Integer tracingMessageId = Integer.valueOf(request.getParameter("tracingMessageId"));
			responseSeenMailService.setMessage(tracingMessageId.toString());
			tracingService.onloadMailByClient(tracingMessageId);
			responseSeenMailService.setResponse(STATUS_OK);
		} catch (Exception e) {
			responseSeenMailService.setResponse(STATUS_ERROR);
			responseSeenMailService.setMessage(e.getMessage());
			logger.error("Error markSeenMail method ", e);
		} finally {
			logger.debug("Ending markSeenMail method");
		}
		return responseSeenMailService;
	}

}

@XmlRootElement(name = "ResponseSeenMailServiceDTO")
class ResponseSeenMailServiceDTO {
	private String response;
	private String message;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
