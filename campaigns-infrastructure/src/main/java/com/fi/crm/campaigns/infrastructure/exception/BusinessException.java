package com.fi.crm.campaigns.infrastructure.exception;

/**
 * This class identifies a Exception considered as business related by different reasons as: 
 * Error related with business domain, logic, prerequisites, postrequisites. 
 * @author am
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    //------------------------------
    //  Attributes
    //------------------------------

	/**
     * User interface message code
     */
    private ErrorCodesEnum code;
    

	/**
     * Custom message to add information to log 
     */
    private String customMessage;
    
    
    //------------------------------
    //  Constructors
    //------------------------------
    
    /**
     * Initialize the business exception
     * @param customMessage
     * @param errorCode
     */
    public BusinessException( String customMessage, ErrorCodesEnum errorCode) {
        super();
        
        this.code = errorCode;
        //TODO intentar obtener el key y dejar el default message en caso de falla
        this.customMessage = customMessage;

    }
    
    /**
     * Initialize the business exception
     * @param cause
     * @param errorCode
     */
    public BusinessException( Throwable cause, ErrorCodesEnum errorCode ) {
        super( "", cause );
        
        this.code = errorCode;
        //TODO intentar obtener el key y dejar el default message en caso de falla
        this.customMessage = errorCode.getDefaultMessage();
    }	
    
    //------------------------------
    //  Methods
    //------------------------------
    
    

    /**
     * Returns a StringBuffer with exception message
     * 
     * @return StringBuffer
     */
    public StringBuffer toStringBuffer() {
        StackTraceElement stackTrace[] = this.getStackTrace();
        StringBuffer buffer = new StringBuffer();
        
        if( this.customMessage != null && !this.customMessage.equals(""))
        {
            buffer.append( this.customMessage + '\n' );
        }
        if( this.code != null )
        {
            buffer.append( this.code.getCodeNumber() + '\n' );
        }
        buffer.append( this.getMessage() + '\n' );
        for( int i = 0; i < stackTrace.length; i++ ) 
        {
            buffer.append( "        at "+stackTrace[i].toString() + '\n' );
        }
        
        return buffer;
    }
    
    public ErrorCodesEnum getCode() {
		return code;
	}
    
    public String getCustomMessage() {
    	return this.customMessage;
    }
}
