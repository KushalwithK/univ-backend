package com.univ.backend.exceptions;

public class TeamMemberNotFoundException extends Exception {
    public TeamMemberNotFoundException() {
        super();
    }

    public TeamMemberNotFoundException(String message) {
        super(message);
    }

    public TeamMemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamMemberNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TeamMemberNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
