package com.univ.backend.controllers;

import com.univ.backend.entities.Expertise;
import com.univ.backend.exceptions.*;
import com.univ.backend.response.ExpertiseDeleteResponse;
import com.univ.backend.response.ExpertisePostRequestResponse;
import com.univ.backend.services.AdminService;
import com.univ.backend.services.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expertise")
public class ExpertiseController {

    @Autowired
    private ExpertiseService service;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Expertise> getAllExpertiseList(@RequestParam(required = false) String name) {
        if (name == null) {
            return service.getAllExpertise();
        }
        return List.of(service.getExpertiseByName(name));
    }

    @PostMapping
    public ExpertisePostRequestResponse expertisePostRequestResponse(
            @RequestParam("bg") MultipartFile bg,
            @RequestParam("name") String name,
            @RequestParam("info") String info,
            @RequestParam("url") String url,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws UnauthorizedException {
        if (adminUserName == null && adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Expertise expertise = service.postExpertiseWithDetails(
                    Expertise.builder()
                            .name(name)
                            .info(info)
                            .url(url)
                            .build(),
                    bg
            );
            return new ExpertisePostRequestResponse(
                    HttpStatus.OK,
                    expertise,
                    expertise.getName() + " was posted in database successfully!",
                    Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }

    }

    @PutMapping
    public ExpertisePostRequestResponse putExpertiseUsingData(
            @RequestParam("id") String id,
            @RequestParam("bg") MultipartFile bg,
            @RequestParam("name") String name,
            @RequestParam("info") String info,
            @RequestParam("url") String url,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws UnauthorizedException, ExpertiseNotFoundException, IOException, ImageFormatException {
        if (adminUserName == null && adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Expertise expertise = service.updateExpertiseUsingId(
                    Expertise.builder()
                            .name(name)
                            .info(info)
                            .url(url)
                            .build(),
                    bg,
                    Long.valueOf(id)
            );
            return new ExpertisePostRequestResponse(
                    HttpStatus.OK,
                    expertise,
                    expertise.getName() + " was posted in database successfully!",
                    Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @DeleteMapping
    public ExpertiseDeleteResponse deleteExpertiseUsingId(
            @RequestParam(name = "id") String id,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws UnauthorizedException, FileNotFoundException, ExpertiseNotFoundException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Expertise deletedExpertise = service.deleteSponsorById(Long.valueOf(id));
            return new ExpertiseDeleteResponse(HttpStatus.OK, deletedExpertise, "Sponsor deleted successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }
}
