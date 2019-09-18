package com.fi.crm.campaigns.web.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.security.SecurityServiceInterface;
import com.vaadin.server.VaadinServlet;

public class CustomCredentialsMatcher extends PasswordMatcher{


	/**
	 * static logger for class CustomCredentialsMatcher
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustomCredentialsMatcher.class);

	private SpringContextHelper helper;
	private SecurityServiceInterface securityService;
	private Boolean encrypt;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		logger.debug("Staring doCredentialsMatch method");
		try {

			String password = String.valueOf( (char[])authcToken.getCredentials());
			logger.debug("encrypt>>> "+encrypt);
			if(encrypt != null && encrypt){
				helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
				securityService = (SecurityServiceInterface) helper.getBean("securityService");
				password = securityService.encryptPassword(password);				
			}
			logger.debug("password>>> "+password);
			//return getPasswordService().passwordsMatch(((SimpleAuthenticationInfo)info).getCredentials(), password);
			
			return (((SimpleAuthenticationInfo)info).getCredentials().toString().equals(password));
		} catch (Exception e) {
			logger.error("Error doCredentialsMatch method ", e);
			return false;
		} finally {
			logger.debug("Ending doCredentialsMatch method");
		}
	}

	public void setEncrypt(Boolean encrypt) {
		logger.debug("encrypt set *"+encrypt);
		this.encrypt = encrypt;
	}

}
