package com.univ.backend.exceptions;

import com.univ.backend.entities.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminNotFoundException extends Exception {

    private Admin admin;

    public AdminNotFoundException() {
        super();
    }

    public AdminNotFoundException(String message, Admin admin) {
        super(message);
        this.admin = admin;
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AdminNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
