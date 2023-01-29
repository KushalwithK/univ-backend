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
            @RequestParam("username") String adminUserName,
            @RequestParam("password") String adminPassword
    ) throws MandatoryFieldFoundEmptyException, UnexpectedServerErrorOccurredException, ImageFormatException, AdminNotFoundException, IncorrectAdminDataException {
        assert (adminUserName != null && adminPassword != null) : "Admin username and password cannot be null.";
        if(adminService.verifyAdmin(adminUserName, adminPassword)) {
            try {
                return service.addSponsor(new Sponsor(name, details), image);
            } catch (IOException e) {
                throw new UnexpectedServerErrorOccurredException(e);
            }
        } else {
            throw new IncorrectAdminDataException("The provided admin data was incorrect!", new Admin(adminUserName, adminPassword));
        }
    }

    @GetMapping
    public List<Sponsor> sponsorGetRequest(@RequestParam(name = "name", required = false) String name) throws SponsorNotFoundException {
        if (name == null) {
            return service.getAllSponsors();
        }
        return service.getSponsorByName(name);
    }

    @PutMapping("/{name}")
    public SponsorUpdateResponse sponsorPutRequest(
            @PathVariable(name = "name") String name,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(name = "name", required = false) String sName,
            @RequestParam(required = false) String details,
            @RequestParam(required = false) String url,
            @RequestParam("username") String adminUserName,
            @RequestParam("password") String adminPassword
    ) throws SponsorNotFoundException, IOException, ImageFormatException, AdminNotFoundException, IncorrectAdminDataException {
        assert (adminUserName != null && adminPassword != null) : "Admin username and password cannot be null.";
        if(adminService.verifyAdmin(adminUserName, adminPassword)) {
            Sponsor sponsor = service.updateSponsorByName(name, image, sName, details, url);
            return new SponsorUpdateResponse(HttpStatus.OK, sponsor, "Sponsor was updated successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new IncorrectAdminDataException("The provided admin data was incorrect!", new Admin(adminUserName, adminPassword));
        }
    }

    @DeleteMapping("/{name}")
    public SponsorDeleteRequestResponse sponsorDeleteRequest(
            @PathVariable(name = "name") String name,
            @RequestParam("username") String adminUserName,
            @RequestParam("password") String adminPassword
    ) throws SponsorNotFoundException, FileNotFoundException, AdminNotFoundException, IncorrectAdminDataException {
        if(adminService.verifyAdmin(adminUserName, adminPassword)) {
            Sponsor deletedSponsor = service.deleteSponsorByName(name);
            return new SponsorDeleteRequestResponse(HttpStatus.OK, deletedSponsor, "Sponsor deleted successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new IncorrectAdminDataException("The provided admin data was incorrect!", new Admin(adminUserName, adminPassword));
        }
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }
}
