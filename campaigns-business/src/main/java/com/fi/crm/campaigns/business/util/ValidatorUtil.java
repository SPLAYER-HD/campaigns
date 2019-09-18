/**
 * 
 */
package com.fi.crm.campaigns.business.util;

import org.apache.commons.validator.EmailValidator;

/**
 * @comapny FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 17/2015 
 */
public class ValidatorUtil {

	/* Constants */
	private static EmailValidator EMAIL_VALIDATOR;

	/**
	 * Expert methos to validate the email format 
	 * @param email
	 * @return Boolean
	 */
	public static boolean validateEmailFormat(String email){

		if (EMAIL_VALIDATOR == null)
			EMAIL_VALIDATOR = EmailValidator.getInstance();

		return EMAIL_VALIDATOR.isValid(email);
	}
}