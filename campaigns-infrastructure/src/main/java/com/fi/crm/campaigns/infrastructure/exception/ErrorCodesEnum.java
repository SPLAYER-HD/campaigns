package com.fi.crm.campaigns.infrastructure.exception;

/**
 * 
 * @author fitideas This class defines the list of the system's error codes for
 *         the possible exceptions containing its default message in english and
 *         the key for other languages
 * 
 *         The taxonomy for declaring the different errorcodes is: For business
 *         exceptions(100xxx) For Security exceptions(200xxx) For technical
 *         exceptions(300xxx)
 */
public enum ErrorCodesEnum {

	/**
	 * Generic exceptions
	 */
	GENERIC_BUSINESS_EXCEPTION(100001, "", ""), 
	GENERIC_SECURITY_EXCEPTION(200001, "", ""),
	GENERIC_TECHNICAL_EXCEPTION(300001, "", ""),
	GENERIC_VALIDATION_EXCEPTION(101001,"",""),
	
	/**
	 *Specific business exceptions 
	 */
	PASSWORD_ENCRYPT_FAILED (100002, "password encrypt failed", "campaign.exception.100002"),
	INSERTION_ERROR(100003,"Error inserting record","ngs.exception.100002"),
	UPDATE_ERROR(100004,"Error updating record","ngs.exception.100003"),
	SELECT_ERROR(100005,"Error retreaving record","ngs.exception.100004"),
	DELETE_NOT_POSSIBLE_BY_REFERENCE (100006, "The entity can't be deleted. It has references.", "ngs.exception.100005"),
	
	
	/**
	 * Specific technical exceptions
	 */
	CACHE_CONFIGURATION_NOT_LOADED(300002,"Error loading cache configuration","campaign.exception.300002"),
	CACHE_NOT_LOADED(300003,"Cache not loaded","campaign.exception.300003"),
	PERSISTENCE_CONFIG_NOT_LOADED(300004, "Persistence config file could not loaded", "campaign.exception.300004"),
	TRANSLATION_NOT_POSIBLE (300005, "Can not translate from BasicDTO object to Model object", "campaign.exception.300005"),
	MORE_THAN_ONE_RESULT (300006, "There are more than one result for the specified query", "campaign.exception.300006"),
	ENCRYPTION_NOT_POSIBLE (300007, "The password encryption is not posible make sure the password and digest are not empty or null", "campaign.exception.300007"),
	NOT_EVALUABLE_EXPRESSION (300008, "The expression can not be evaluated, one or more values are not valid", "campaign.exception.300008"),
	FILE_STORING_FAILED (300009, "The uploaded files could not be stored", "campaign.exception.300009"),
	SEND_MAIL_FAILED (300010, "Failed send mail", "campaign.exception.300010"),
	
	/**
	 * Specific security exceptions
	 */
	INVALID_USERNAME_OR_PASSWORD(200002,"Invalid username or password", "campaign.exception.200002"),
	INVALID_USER_STATUS(200003,"Invalid user status","campaign.exception.200003"),
	NON_REGISTERED_USER(200004,"User non registered","campaign.exception.200004"),
	PASSWORD_CONFIRMATION_DOES_NOT_MATCH(200005,"The password confirmation does not match","campaign.exception.200005"),
	SESSION_TIMEOUT(200006,"The session has expired","campaign.exception.200006"),
	SAME_PASSWORD (200007,"The new password can not be equals to the old password","campaign.exception.200007"),
	USERNAME_ALREADY_IN_USE (200008,"The username is already registered in the system","campaign.exception.200008"),
	
	
	
	;
	
	private long codeNumber;
	private String defaultMessage;
	private String messagekey;

	private ErrorCodesEnum(long codeNumber, String defaultMessage,
			String messagekey) {
		this.codeNumber = codeNumber;
		this.defaultMessage = defaultMessage;
		this.messagekey = messagekey;
	}

	public long getCodeNumber() {
		return codeNumber;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public String getMessagekey() {
		return messagekey;
	}

}
