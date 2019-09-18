package com.fi.crm.campaigns.web.extensions;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.UI;

@JavaScript({"http://static.twilio.com/libs/twiliojs/1.2/twilio.min.js","call_extension.js"})
public class CallExtension extends AbstractJavaScriptExtension {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CallExtension(String token) {
		setToken(token);
	}

	private void setToken(String token) {
		getState().token = token;
	}

	@Override
	protected CallExtensionState getState() {
		return (CallExtensionState) super.getState();
	}

	public void extend(UI target) {
		super.extend(target);
	}

	public void initCall() {
		callFunction("initCall");
	}

	public void makeCall(String phoneNumber) {
		callFunction("makeCall", phoneNumber);
	}

	public void hangUp(){
		callFunction("hangUp");
	}
}