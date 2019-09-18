package com.fi.crm.campaigns.common.dto;

public class LoginCredentialsDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7923019161499248246L;

	private String userName;
	private String password;
	private String terminal;
	private String appClient;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
