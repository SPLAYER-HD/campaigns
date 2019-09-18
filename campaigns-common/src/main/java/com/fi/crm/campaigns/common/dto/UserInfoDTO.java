package com.fi.crm.campaigns.common.dto;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class UserInfoDTO implements BaseDTO{

	/**
	 * UID for class serialization
	 */
	private static final long serialVersionUID = 6707076242534964148L;

	/**
	 * User id
	 */
	private String userCode;
	
	/**
	 * User locale
	 */
	private String fullName;
	
	/**
	 * Ip of the client
	 */
	private String terminal;
	/**
	 * Application used by the user for access
	 */
	private String appClient;
	/**
	 * Registered time
	 */
	private Calendar regTime;
	/**
	 * Login time
	 */
	private Calendar loginTime;
	
	/**
	 * User locale
	 */
	private Locale userLocale;
	
	/**
	 * List of navegation
	 */
	private HashMap<String, Object> navigation;

	public Locale getUserLocale() {
		return userLocale;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getAppClient() {
		return appClient;
	}

	public void setAppClient(String appClient) {
		this.appClient = appClient;
	}

	public Calendar getRegTime() {
		return regTime;
	}

	public void setRegTime(Calendar regTime) {
		this.regTime = regTime;
	}

	public Calendar getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Calendar loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the navigation
	 */
	public HashMap<String, Object> getNavigation() {
		return navigation;
	}

	/**
	 * @param navigation the navigation to set
	 */
	public void setNavigation(HashMap<String, Object> navigation) {
		this.navigation = navigation;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
