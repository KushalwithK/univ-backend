package com.univ.backend.exceptions;

import java.nio.file.FileAlreadyExistsException;

public class ImageAlreadyExistsException extends FileAlreadyExistsException {
    public ImageAlreadyExistsException(String file) {
        super(file);
    }

    public ImageAlreadyExistsException(String file, String other, String reason) {
        super(file, other, reason);
    }
}
