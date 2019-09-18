package com.fi.crm.campaigns.infrastructure.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
//import java.util.ArrayList;
//import java.util.HashMap;
//import javax.mail.Authenticator;
//import javax.mail.BodyPart;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	public void send(String sender, String addressee, String subject, String body, String mailServerIP, String mailServerPort, List<String> attachments) {

		Properties mailServerProperties = new Properties();
		mailServerProperties.put("mail.smtp.host", mailServerIP);
		System.out.println("mail.smtp.host=" + mailServerIP);
		System.out.println("mail.smtp.port=" + mailServerPort);
		mailServerProperties.put("mail.smtp.port", mailServerPort);

		// Sesion new to changed ip and port
		Session session = Session.getInstance(mailServerProperties);
		// MimeMessage
		MimeMessage messageToSend = new MimeMessage(session);
		// MultiParte to add text and attach
		Multipart multipart = new MimeMultipart();

		try {
			System.out.println("sender####"+sender);
			messageToSend.setFrom(sender);
			messageToSend.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee));
			messageToSend.setSubject(subject);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(body, "text/html");
			multipart.addBodyPart(mimeBodyPart);

			if (attachments != null) {
				for (String filePath : attachments) {
					mimeBodyPart = new MimeBodyPart();
					File f = new File(filePath);					
					if (f.exists()) {
						System.out.println(f.getName());
						DataSource source = new FileDataSource(filePath);
						mimeBodyPart.setDataHandler(new DataHandler(source));
						mimeBodyPart.setFileName(f.getName());
						multipart.addBodyPart(mimeBodyPart);
					}
				}
			}

			// messageToSend.setText(body);
			// messageToSend.setContent(body, "text/html");
			messageToSend.setContent(multipart);
			Transport.send(messageToSend);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error:SendMail " + ex);
		}
	}

	public static void main(String[] args) {
		SendEmail s = new SendEmail();
		List<String> attachments = new ArrayList<String>();
		attachments.add("D:/pse.pdf");
		s.send("comunicaciones@test4.com.co", "diego.torres@fitideas.co", "asuntoq", "body", "192.168.1.43", "25", null);
		List<String> receptores = new ArrayList<String>();
		receptores.add("diego.torres@fitideas.co");
		List<String> adjuntos = new ArrayList<String>();
		adjuntos.add("D://pse.7z");
//		s.envia("192.168.1.26", "25", "ing.diego.torres95@gmail.com", "asunto", receptores, "msajeda", adjuntos);
		// s.enviaGoogleApp("test.fitideas@gmail.com", "asunto", receptores,
		// "<html>mensaje</html>", adjuntos);
	}
//
//	public boolean envia(String ip, String port, String emisor, String asunto, List<String> receptores, String mensaje, List<String> adjuntos) {
//		boolean envioExitoso = true;
//		try {
//			Properties props = System.getProperties();
//
//			// Se define el servidor de correos
//			props.put("mail.smtp.host", ip);
//			props.put("mail.smtp.port", port);
//
//			// Se obtiene sesi&oacute;n desde el servidor de correos
//			Session session = Session.getInstance(props, null);
//			session.setDebug(true);
//
//			MimeMessage message = new MimeMessage(session);
//			InternetAddress[] dest = new InternetAddress[receptores.size()];
//			for (int i = 0; i < dest.length; i++) {
//				dest[i] = new InternetAddress(receptores.get(i));
//			}
//
//			// Se define qui&eacute;n es el emisor del e-mail
//			message.setFrom(new InternetAddress(emisor));
//			InternetAddress[] replyTo = new InternetAddress[1];
//			replyTo[0] = new InternetAddress(emisor);
//			message.setReplyTo(replyTo);
//			// Se definen el o los destinatarios
//			message.addRecipients(Message.RecipientType.TO, dest);
//			// message.addRecipients(Message.RecipientType.CC, dest);
//			// message.addRecipients(Message.RecipientType.BCC, dest);
//
//			// Se defina el asunto del e-mail
//			message.setSubject(asunto);
//
//			// Se seteo el mensaje del e-mail
//			MimeBodyPart messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setContent(mensaje, "text/html");
//
//			Multipart multipart = new MimeMultipart();
//			multipart.addBodyPart(messageBodyPart);
//
//			// Se adjuntan los archivos al correo
//			if (adjuntos != null && adjuntos.size() > 0) {
//				for (String rutaAdjunto : adjuntos) {
//					messageBodyPart = new MimeBodyPart();
//					File f = new File(rutaAdjunto);
//					if (f.exists()) {
//						System.out.println(f.getName());
//						DataSource source = new FileDataSource(rutaAdjunto);
//						messageBodyPart.setDataHandler(new DataHandler(source));
//						messageBodyPart.setFileName(f.getName());
//						multipart.addBodyPart(messageBodyPart);
//					}
//				}
//			}
//
//			// Se junta el mensaje y los archivos adjuntos
//			message.setContent(multipart);
//
//			// Se env&iacute;a el e-mail
//			Transport.send(message);
//		} catch (MessagingException  e) {
//			System.out.println(e);
//			envioExitoso = false;
//		} finally {
//			
//		}
//		return envioExitoso;
//	}
//
//	private class SMTPAuthenticator extends Authenticator {
//		private String dEmail;
//		private String dPassword;
//
//		public SMTPAuthenticator(String email, String password) {
//			dEmail = email;
//			dPassword = password;
//		}
//
//		public PasswordAuthentication getPasswordAuthentication() {
//			return new PasswordAuthentication(dEmail, dPassword);
//		}
//	}
//
//	public boolean enviaGoogleApp(String emisor, String asunto, List<String> receptores, String mensaje, List<String> adjuntos) {
//		boolean envioExitoso = true;
//		String emailGetCursos = "test.fitideas@gmail.com";
//		String password = "Fitideas2015";
//		Properties props = new Properties();
//		props.put("mail.smtp.user", emisor);
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.auth", "true");
//
//		try {
//
//			Authenticator auth = new SMTPAuthenticator(emailGetCursos, password);
//			Session session = Session.getInstance(props, auth);
//
//			MimeMessage message = new MimeMessage(session);
//			InternetAddress[] dest = new InternetAddress[receptores.size()];
//			for (int i = 0; i < dest.length; i++) {
//				dest[i] = new InternetAddress(receptores.get(i));
//			}
//
//			// Se define qui&eacute;n es el emisor del e-mail
//			message.setFrom(new InternetAddress(emisor));
//			InternetAddress[] replyTo = new InternetAddress[1];
//			replyTo[0] = new InternetAddress(emisor);
//			message.setReplyTo(replyTo);
//			// Se definen el o los destinatarios
//			message.addRecipients(Message.RecipientType.TO, dest);
//			// message.addRecipients(Message.RecipientType.CC, dest);
//			// message.addRecipients(Message.RecipientType.BCC, dest);
//
//			// Se defina el asunto del e-mail
//			message.setSubject(asunto);
//
//			// Se seteo el mensaje del e-mail
//			MimeBodyPart messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setContent(mensaje, "text/html");
//
//			Multipart multipart = new MimeMultipart();
//			multipart.addBodyPart(messageBodyPart);
//
//			// Se adjuntan los archivos al correo
//			if (adjuntos != null && adjuntos.size() > 0) {
//				for (String rutaAdjunto : adjuntos) {
//					messageBodyPart = new MimeBodyPart();
//					File f = new File(rutaAdjunto);
//
//					if (f.exists()) {
//						System.out.println(f.getName());
//						DataSource source = new FileDataSource(rutaAdjunto);
//						messageBodyPart.setDataHandler(new DataHandler(source));
//						messageBodyPart.setFileName(f.getName());
//						multipart.addBodyPart(messageBodyPart);
//					}
//				}
//			}
//
//			// Se junta el mensaje y los archivos adjuntos
//			message.setContent(multipart);
//
//			// Se env&iacute;a el e-mail
//			Transport.send(message);
//		} catch (Exception e) {
//			System.out.println(e);
//			envioExitoso = false;
//		} finally {
//			// Se eliminan del servidor los archivos adjuntos
//			
//		}
//		return envioExitoso;
//	}
}