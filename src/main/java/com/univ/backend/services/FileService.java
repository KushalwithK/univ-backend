package com.univ.backend.services;

import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.models.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    ImageData uploadImage(String path, MultipartFile image) throws IOException, ImageFormatException;
    InputStream getInputStreamUsingPath(String path, String fileName) throws FileNotFoundException;

    void deleteImageUsingPath(String path, String fileName) throws FileNotFoundException;
}
