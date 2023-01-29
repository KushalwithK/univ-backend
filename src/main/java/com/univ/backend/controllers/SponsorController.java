package com.univ.backend.controllers;

import com.univ.backend.entities.Admin;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.*;
import com.univ.backend.response.*;
import com.univ.backend.services.AdminService;
import com.univ.backend.services.SponsorService;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sponsor")
//@CrossOrigin(origins = "https://localhost:5173")
public class SponsorController {

    @Autowired
    private SponsorService service;

    @Autowired
    private AdminService adminService;

    @PostMapping(consumes = "multipart/form-data")
    public SponsorPostRequestResponse sponsorPostRequest(
            @RequestParam("image") MultipartFile image,
            @RequestParam String name,
            @RequestParam String details,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws MandatoryFieldFoundEmptyException, UnexpectedServerErrorOccurredException, ImageFormatException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            try {
                return service.addSponsor(new Sponsor(name, details), image);
            } catch (IOException e) {
                throw new UnexpectedServerErrorOccurredException(e);
            }
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @GetMapping
    public List<Sponsor> sponsorGetRequest(@RequestParam(name = "name", required = false) String name) throws SponsorNotFoundException {
        if (name == null) {
            return service.getAllSponsors();
        }
        return service.getSponsorByName(name);
    }

    @PutMapping
    public SponsorUpdateResponse sponsorPutRequest(
            @RequestParam(name = "id") String id,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(name = "name", required = false) String sName,
            @RequestParam(required = false) String details,
            @RequestParam(required = false) String url,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws SponsorNotFoundException, IOException, ImageFormatException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Sponsor sponsor = service.updateSponsorById(Long.valueOf(id), image, sName, details, url);
            return new SponsorUpdateResponse(HttpStatus.OK, sponsor, "Sponsor was updated successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @DeleteMapping
    public SponsorDeleteRequestResponse sponsorDeleteRequest(
            @RequestParam(name = "id") String id,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws SponsorNotFoundException, FileNotFoundException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Sponsor deletedSponsor = service.deleteSponsorById(Long.valueOf(id));
            return new SponsorDeleteRequestResponse(HttpStatus.OK, deletedSponsor, "Sponsor deleted successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }
}
