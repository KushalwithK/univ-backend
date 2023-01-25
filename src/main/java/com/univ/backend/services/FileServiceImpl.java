package com.univ.backend.services;

import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.models.ImageData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public ImageData uploadImage(String path, MultipartFile image) throws IOException, ImageFormatException {
        if (!(Objects.equals(image.getContentType(), MediaType.IMAGE_PNG_VALUE) || Objects.equals(image.getContentType(), MediaType.IMAGE_JPEG_VALUE))) {
            throw new ImageFormatException("Provided file is not an image!");
        }
        //Name of the image.
        String originalName = image.getOriginalFilename();
        assert originalName != null;
        String usableName = originalName.replace(" ", "_").toLowerCase();

        //Full Path where the image will be saved
        String fullPath = path + usableName;

        //Creating folder if not present
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        //Copying files to the fullPath
        Files.copy(image.getInputStream(), Paths.get(fullPath));

        return new ImageData(usableName, image.getContentType(), fullPath);
    }

    @Override
    public InputStream getInputStreamUsingPath(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + fileName;
        InputStream stream = new FileInputStream(fullPath);
        return stream;
    }


}
