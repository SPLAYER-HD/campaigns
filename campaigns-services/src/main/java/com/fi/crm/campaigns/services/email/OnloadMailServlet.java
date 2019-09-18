package com.fi.crm.campaigns.services.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Servlet implementation class OnloadMailServlet
 */
@WebServlet("/OnloadMailServlet")
public class OnloadMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * static logger for class OnloadMailServlet
	 */
	private static final Logger logger = Logger.getLogger("OnloadMailServlet.class");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OnloadMailServlet() {
		super();
		System.out.println("OnloadMailServlet");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		logger.info("Staring OnloadMailServlet.doGet method");
		try {
			logger.info("request.getAttribute(tracingMessageId)>>> " + request.getParameter("tracingMessageId"));
			Properties path = new Properties();
			path.load(this.getClass().getClassLoader().getResourceAsStream("WEB-INF/path.properties"));
			// create HTTP Client
			HttpClient httpClient = HttpClientBuilder.create().build();
			logger.info("path.getProperty(url.SeenMailService)===="+path.getProperty("url.SeenMailService"));
			// Create new getRequest with below mentioned URL
//			HttpGet getRequest = new HttpGet(path.getProperty("url.SeenMailService") + request.getParameter("tracingMessageId"));
			HttpGet getRequest = new HttpGet("http://192.168.1.36:8081/campaigns-web/html/SeenMailService.xml?tracingMessageId=" + request.getParameter("tracingMessageId"));

			// Add additional header to getRequest which accepts application/xml
			// data
			getRequest.addHeader("accept", "application/xml");

			// Execute your request and catch response
			HttpResponse response = httpClient.execute(getRequest);
			logger.info("response==="+response);
			// Check for HTTP response code: 200 = success
			logger.info("response.getStatusLine().getStatusCode()"+response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			// Get-Capture Complete application/xml body response
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.out.println("============Output:============");

			// Simply iterate through XML response and show on console.
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			// tracingService.onloadMailByClient(new
			// Integer(request.getParameter("tracingMessageId").toString()));
		} catch (Exception e) {
			logger.info("OnloadMailServlet:Exception "+e);
			logger.log(Level.SEVERE, "Error OnloadMailServlet.doGet method ");
		} finally {
			logger.info("Ending OnloadMailServlet.doGet method");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}

