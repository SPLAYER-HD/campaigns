/**
 * 
 */
package com.fi.crm.campaigns.infrastructure.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @version 1.0
 * @date Jan, 26/2015
 */
public class SendSMS {


	/**
	 * static logger for class SendSMS
	 */
	private static final Logger logger = LoggerFactory.getLogger(SendSMS.class);

	/**
	 * Metodo encargado de realizar el envio de mensajes de texto a numero de telefono celular
	 */
	public static void sendSms(String appKey, String appSecret, String phoneIndCol, String smsUrl, String phoneNumber, String message)throws Exception{
		logger.debug("Staring sendSms method");
		try{
			System.out.println("Send SMS: appKey = "+appKey+ " -appSecret= "+appSecret+ " -phoneIndCol= "+phoneIndCol +""
					+ " -smsUrl= "+smsUrl+" -phoneNumber= "+phoneNumber+ " -message= "+message);
			URL url = new URL(smsUrl + phoneIndCol + phoneNumber);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			String userCredentials = "application\\" + appKey + ":" + appSecret;
			byte[] encoded = Base64.encodeBase64(userCredentials.getBytes());
			String basicAuth = "Basic " + new String(encoded);
			connection.setRequestProperty("Authorization", basicAuth);

			String postData = "{\"Message\":\"" + message + "\"}";
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());

			StringBuilder response = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = br.readLine()) != null)
				response.append(line);

			br.close();
			os.close();

		}catch (Exception e){
			logger.error("Error sendSms method");
			throw e;
		}finally{
			logger.debug("Finished sendSms method");
		}
	}

	/**
	 * Test send message
	 * @param phoneNumber
	 * @param message
	 */
	public static void testSendSMS(String phoneNumber, String message){

		try {

			String appKey = "aa25b9cb-df9d-4b32-8207-f553ae5956b8";
			String appSecret = "r7LoaA7KckCd4uBscQ5L9Q==";

			URL url = new URL("https://messagingapi.sinch.com/v1/sms/" + phoneNumber);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			String userCredentials = "application\\" + appKey + ":" + appSecret;
			byte[] encoded = Base64.encodeBase64(userCredentials.getBytes());
			String basicAuth = "Basic " + new String(encoded);
			connection.setRequestProperty("Authorization", basicAuth);

			String postData = "{\"Message\":\"" + message + "\"}";
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());

			StringBuilder response = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			while ( (line = br.readLine()) != null)
				response.append(line);

			br.close();
			os.close();

			System.out.println(response.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String sendSMSITCloud(String user, String password, String phoneInd, String smsUrl, String phoneNumber, String message)throws Exception{
		logger.debug("Staring sendSMSITCloud method");
		try {
			System.out.println("####################################");
			System.out.println(user);
			System.out.println(password);
			System.out.println(phoneInd);
			System.out.println(phoneNumber);
			System.out.println(smsUrl);
			System.out.println(phoneNumber);
			System.out.println(message);
			System.out.println("####################################");
			System.out.println("StringEscapeUtils.escapeHtml3(message)=="+StringEscapeUtils.escapeHtml3(message));
			System.out.println("StringEscapeUtils.escapeHtml3(message)=="+StringEscapeUtils.escapeHtml4(message));
			URL urlnew = new URL(smsUrl+"?user="+user+"&password="+password+"&GSM="+phoneInd+phoneNumber+"&SMSText="+URLEncoder.encode(message,"UTF-8"));
			//URL urlnew = new URL(smsUrl);
			HttpsURLConnection conn = (HttpsURLConnection)urlnew.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			//conn.setRequestProperty("Content-type", "application/json");
			conn.connect();

//			String data = URLEncoder.encode("user", "UTF-8") + "=" + user;
//	        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
//	        data += "&" + URLEncoder.encode("GSM", "UTF-8") + "=" + URLEncoder.encode(phoneInd+phoneNumber, "UTF-8");
//	        data += "&" + URLEncoder.encode("SMSText", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
//	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());		       
//	        wr.write(data);
//	        wr.flush();
//	        logger.debug("###data###"+data);
			
//			String postData = "{\"user\":\"" + user + 
//					"\",\"password\":\""+password+"\",\"GSM\":\""+phoneInd+phoneNumber+"\",\"SMStext\":\""+message+"\"}";
//			OutputStream os = conn.getOutputStream();
//			os.write(postData.getBytes());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response =in.readLine();
			in.close();
			logger.debug("sendSMSITCloud RESPONSE====="+response);
			return response.split(",")[1].replace("<br>", "").trim();
		} catch (Exception e) {
			logger.error("Error sendSMSITCloud method ", e);
			throw e;
		} finally {
			logger.debug("Ending sendSMSITCloud method");
		}
		
	}
	
//	public static void main(String args[]){
//		String message = "dd dd dd ";
//		try {
//			System.out.println(URLEncoder.encode(message,"UTF-8"));
//			URL urlnew;
//			urlnew = new URL("https://sistemasmasivos.com/itcloud/api/sendsms/send.php"+
//				"?user="+"miguel.mojica@lariviera.com.co"+"&password="+"O8xAv-0GNG"+"&GSM="+"573124668532"+"&SMSText="+URLEncoder.encode(message,"UTF-8"));
//			
//			//URL urlnew = new URL(smsUrl);
//			HttpsURLConnection conn = (HttpsURLConnection)urlnew.openConnection();
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.setRequestMethod("GET");
//			conn.setUseCaches(false);
//			//conn.setRequestProperty("Content-type", "application/json");
//			conn.connect();
//
////			String data = URLEncoder.encode("user", "UTF-8") + "=" + user;
////	        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
////	        data += "&" + URLEncoder.encode("GSM", "UTF-8") + "=" + URLEncoder.encode(phoneInd+phoneNumber, "UTF-8");
////	        data += "&" + URLEncoder.encode("SMSText", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
////	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());		       
////	        wr.write(data);
////	        wr.flush();
////	        logger.debug("###data###"+data);
//			
////			String postData = "{\"user\":\"" + user + 
////					"\",\"password\":\""+password+"\",\"GSM\":\""+phoneInd+phoneNumber+"\",\"SMStext\":\""+message+"\"}";
////			OutputStream os = conn.getOutputStream();
////			os.write(postData.getBytes());
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			//String response =in.readLine();
//			in.close();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}