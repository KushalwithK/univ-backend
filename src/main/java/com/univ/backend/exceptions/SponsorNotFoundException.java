package com.univ.backend.exceptions;

public class SponsorNotFoundException extends Exception {
    public SponsorNotFoundException() {
        super();
    }

    public SponsorNotFoundException(String message) {
        super(message);
    }

    public SponsorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SponsorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SponsorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
