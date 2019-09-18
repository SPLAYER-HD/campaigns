package com.fi.crm.campaigns.infrastructure.exception;

/**
 * This class identifies a Exception considered as technical by different reasons as: 
 * Absence of required configuration, Lack of connectivity with a external system, 
 * Development related error identified at runtime. 
 * @author am
 *
 */
public class TechnicalException extends RuntimeException {

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
    private String code;
    
    /**
     * Custom message to add information to log 
     */
    private String customMessage;
    
    
    //------------------------------
    //  Constructors
    //------------------------------
    
    /**
     * Initialize the technical exception
     * @param errorCode
     */
    public TechnicalException( ErrorCodesEnum errorCode ) {
        super();
        
        this.code = String.valueOf(errorCode.getCodeNumber());
        //TODO intentar obtener el key y dejar el default message en caso de falla
        this.customMessage = errorCode.getDefaultMessage();

    }
    
    /**
     * Initialize the technical exception
     * @param cause
     * @param errorCode
     */
    public TechnicalException( Throwable cause, ErrorCodesEnum errorCode ) {
        super( "", cause );
        this.code = String.valueOf(errorCode.getCodeNumber());
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
            buffer.append( this.code + '\n' );
        }
        buffer.append( this.getMessage() + '\n' );
        for( int i = 0; i < stackTrace.length; i++ ) 
        {
            buffer.append( "        at "+stackTrace[i].toString() + '\n' );
        }
        
        return buffer;
    }
}
