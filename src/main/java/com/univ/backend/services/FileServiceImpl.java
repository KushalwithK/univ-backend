package com.univ.backend.services;

import com.univ.backend.exceptions.ImageAlreadyExistsException;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.models.ImageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public ImageData uploadImage(String path, MultipartFile image) throws IOException, ImageFormatException {
        if (!(Objects.equals(image.getContentType(), MediaType.IMAGE_PNG_VALUE) || Objects.equals(image.getContentType(), MediaType.IMAGE_JPEG_VALUE))) {
            throw new ImageFormatException("Provided file is not an image!");
        }
        long timeStamp = Calendar.getInstance().getTime().getTime();
        //Name of the image.
        String originalName = image.getOriginalFilename();
        assert originalName != null;
        String changedName = originalName.replace(" ", "_").toLowerCase().substring(0, originalName.lastIndexOf("."));
        String usableName = changedName + "-" + timeStamp + originalName.substring(originalName.lastIndexOf("."));


        //Full Path where the image will be saved
        String fullPath = path + usableName;

        if(new File(fullPath).exists()) {
            throw new ImageAlreadyExistsException(usableName, path, "Provided image already exists.");
        }

        //Creating folder if not present
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        //Copying files to the fullPath
        Files.copy(image.getInputStream(), Paths.get(fullPath));

        return new ImageData(usableName, image.getContentType(), path);
    }

    @Override
    public InputStream getInputStreamUsingPath(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + fileName;
        InputStream stream = new FileInputStream(fullPath);
        return stream;
    }

    @Override
    public void deleteImageUsingPath(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + fileName;
        File file = new File(path);
        if(file.exists()) {
            File f = new File(file, fileName);
            f.delete();
            return;
        }
        throw new FileNotFoundException("File with name " + fileName + " not found in the database!");
    }


}
