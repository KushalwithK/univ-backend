package com.univ.backend.exceptions;

public class MandatoryFieldFoundEmptyException extends Exception {
    public MandatoryFieldFoundEmptyException() {
        super();
    }

    public MandatoryFieldFoundEmptyException(String message) {
        super(message);
    }

    public MandatoryFieldFoundEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MandatoryFieldFoundEmptyException(Throwable cause) {
        super(cause);
    }

    protected MandatoryFieldFoundEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
