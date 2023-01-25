package com.univ.backend.exceptions;

public class UnexpectedServerErrorOccurredException extends Exception {

    public UnexpectedServerErrorOccurredException() {
        super();
    }

    public UnexpectedServerErrorOccurredException(String message) {
        super(message);
    }

    public UnexpectedServerErrorOccurredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedServerErrorOccurredException(Throwable cause) {
        super(cause);
    }

    protected UnexpectedServerErrorOccurredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
