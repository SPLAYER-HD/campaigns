package com.fi.crm.campaigns.web.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.UI;

public class Messages {
	/**
	 * static logger for class Messages
	 */
	final static Logger logger = LoggerFactory.getLogger(Messages.class);
	
	private static final String BUNDLE_NAME = "com.fi.crm.campaigns.web.util.i18n.messages"; //$NON-NLS-1$

	private HashMap<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();
	private static Messages messages = new Messages();

	private Messages() {
	}

	public static String getString(String key, Locale locale) {
		ResourceBundle rb = null;
		try {
			rb = messages.bundles.get(locale);
			
			logger.debug(BUNDLE_NAME + "_" + locale.toString());
			if(rb == null) {
				messages.bundles.put(locale, ResourceBundle
				.getBundle(BUNDLE_NAME + "_" + locale.toString(), locale));
				
				logger.debug(BUNDLE_NAME + "_" + locale.toString());
				rb = messages.bundles.get(locale);
			}
			
			if (rb == null) {
				return '!' + key + '!';
			}

			return rb.getString(key);
		} catch (MissingResourceException e) {
			if (rb == null) {
				messages.bundles.put(locale, ResourceBundle
						.getBundle(BUNDLE_NAME));
				rb = messages.bundles.get(locale);
				return rb.getString(key);
			}
			return '!' + key + '!';
		}
	}

	public static String getString(String key) {
		return getString(key, UI.getCurrent().getLocale());
	}

}
