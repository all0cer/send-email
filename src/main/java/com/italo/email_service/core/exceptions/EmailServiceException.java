package com.italo.email_service.core.exceptions;


public class EmailServiceException extends RuntimeException {

    public EmailServiceException(Exception e) {
        super(e);
    }
    
    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailServiceException(String message) {
        super(message);
    }
    
}
