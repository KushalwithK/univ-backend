package com.univ.backend.exceptions;

public class ExpertiseNotFoundException extends Exception {

    public ExpertiseNotFoundException() {
        super();
    }

    public ExpertiseNotFoundException(String message) {
        super(message);
    }

    public ExpertiseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpertiseNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ExpertiseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
