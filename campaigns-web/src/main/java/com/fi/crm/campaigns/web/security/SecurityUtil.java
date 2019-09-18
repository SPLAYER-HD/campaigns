package com.fi.crm.campaigns.web.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.security.SecurityServiceInterface;
import com.fi.crm.campaigns.common.dto.LoginCredentialsDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.SessionUtil;
import com.fi.crm.campaigns.web.util.SpringContextHelper;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

public class SecurityUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);
	

	private SpringContextHelper helper;
	private SecurityServiceInterface securityService;
	
	public void login(String username, String password) throws AuthenticationException{
		UsernamePasswordToken token;
		
		helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
		securityService = (SecurityServiceInterface) helper.getBean("securityService");
		token = new UsernamePasswordToken(username.toUpperCase(), password);
		// ”Remember Me” built-in, just do this:
		token.setRememberMe(true);
		try{
			// With most of Shiro, you'll always want to make sure you're working with the currently executing user,
			// referred to as the subject
			Subject currentUser = SecurityUtils.getSubject();
			System.out.println("token.getCredentials()"+token.getCredentials().toString());
			System.out.println("token.getPassword()"+token.getPassword().toString());
			// Authenticate
			currentUser.login(token);
			
	
			LoginCredentialsDTO credentials = new LoginCredentialsDTO();
			credentials.setUserName(username.toUpperCase());
			credentials.setPassword(password);
			credentials.setTerminal(CommonUtil.getTerminal());
			UserInfoDTO userInfo = null;
			try {
				userInfo = securityService.login(credentials);
				SessionUtil.setUserInfo(userInfo);
			} catch (BusinessException e) {
				LOGGER.error("Error trying to login", e);
			}
		} catch (AuthenticationException e) {
			LOGGER.error("Error" ,e);
			throw e;
		}
	}


	public void logout() {
		UI.getCurrent().close();

		Subject currentUser = SecurityUtils.getSubject();

		if (currentUser.isAuthenticated()){
			try {
				currentUser.logout();
				UI.getCurrent().getSession().close();
			} catch (Exception e){
				LOGGER.error("Error trying to logout",e );
			}
		}
		UI.getCurrent().getPage().setLocation("/campaigns-web");
	}


	public static boolean isUserAuthenticated() {
	    
    	Subject currentUser = SecurityUtils.getSubject();
    	return currentUser.isAuthenticated();
	}
}
