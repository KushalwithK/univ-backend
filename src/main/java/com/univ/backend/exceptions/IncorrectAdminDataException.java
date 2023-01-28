package com.univ.backend.exceptions;

import com.univ.backend.entities.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectAdminDataException extends Exception{

    private Admin admin;

    public IncorrectAdminDataException() {
        super();
    }

    public IncorrectAdminDataException(String message, Admin admin) {
        super(message);
        this.admin = admin;
    }

    public IncorrectAdminDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectAdminDataException(Throwable cause) {
        super(cause);
    }

    protected IncorrectAdminDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
