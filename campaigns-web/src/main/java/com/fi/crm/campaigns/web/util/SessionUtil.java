package com.fi.crm.campaigns.web.util;

import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.web.extensions.CallExtension;
import com.vaadin.server.VaadinSession;

public class SessionUtil {

	private static final String USER_INFO = "UserInfo";
	private static final String CALL_EXTENSION = "CallExtension";

	public static UserInfoDTO getUserInfo(){
		UserInfoDTO infoUsuario = (UserInfoDTO) VaadinSession.getCurrent().getAttribute(USER_INFO);
		return infoUsuario;
	}

	public static void setUserInfo(UserInfoDTO userInfo) {
		VaadinSession.getCurrent().setAttribute(USER_INFO, userInfo);
	}

	public static CallExtension getCallExtension(){
		CallExtension infoUsuario = (CallExtension)VaadinSession.getCurrent().getAttribute(CALL_EXTENSION);
		return infoUsuario;
	}

	public static void setCallExtension(CallExtension callExtension) {
		VaadinSession.getCurrent().setAttribute(CALL_EXTENSION, callExtension);
	}
}