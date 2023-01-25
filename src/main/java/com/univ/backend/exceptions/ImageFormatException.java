package com.univ.backend.exceptions;

public class ImageFormatException extends Exception {

    public ImageFormatException() {
        super();
    }

    public ImageFormatException(String message) {
        super(message);
    }

    public ImageFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageFormatException(Throwable cause) {
        super(cause);
    }

    protected ImageFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
