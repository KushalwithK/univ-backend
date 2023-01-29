package com.univ.backend.controllers;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.Admin;
import com.univ.backend.exceptions.AdminNotFoundException;
import com.univ.backend.exceptions.IncorrectAdminDataException;
import com.univ.backend.services.AdminService;
import com.univ.backend.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "https://localhost:5173")
public class AggregationController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImageUsingName(
            @PathVariable("name") String imageName,
            @RequestParam("username") String adminUserName,
            @RequestParam("password") String adminPassword,
            HttpServletResponse response
    ) throws IOException, AdminNotFoundException, IncorrectAdminDataException {
        assert (adminUserName != null && adminPassword != null) : "Admin username and password cannot be null.";
        if(adminService.verifyAdmin(adminUserName, adminPassword)) {
            InputStream resource = fileService.getInputStreamUsingPath(Constant.IMAGE_BASE_URL, imageName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource, response.getOutputStream());
        } else {
            throw new IncorrectAdminDataException("The provided admin data was incorrect!", new Admin(adminUserName, adminPassword));
        }
    }
}
