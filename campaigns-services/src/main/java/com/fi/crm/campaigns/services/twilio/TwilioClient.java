package com.fi.crm.campaigns.services.twilio;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.Number;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

/**
 * Servlet implementation class Client
 */
@WebServlet("/TwilioClient")
public class TwilioClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Logger logger = Logger.getLogger("TwilioClient");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwilioClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a TwiML response and add our friendly message.
        TwiMLResponse twiml = new TwiMLResponse();
        Dial dial = new Dial();
        dial.setCallerId("16693420994");
        Number number = new Number(request.getParameter("PhoneNumber"));
       
        System.out.println("#####request.getParameterMap()### "+request.getParameterMap());
        try {
        	
        	dial.append(number);
            twiml.append(dial);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
