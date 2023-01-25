package com.univ.backend.controllers;

import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.exceptions.UnexpectedServerErrorOccurredException;
import com.univ.backend.response.*;
import com.univ.backend.services.SponsorService;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sponsor")
//@CrossOrigin(origins = "https://localhost:5173")
public class SponsorController {

    @Autowired
    private SponsorService service;
    @PostMapping(consumes = "multipart/form-data")
    public SponsorPostRequestResponse sponsorPostRequest(@RequestParam("image") MultipartFile image, @RequestParam String name, @RequestParam String details) throws MandatoryFieldFoundEmptyException, UnexpectedServerErrorOccurredException, ImageFormatException {
        try {
            return service.addSponsor(new Sponsor(name, details), image);
        } catch (IOException e) {
            throw new UnexpectedServerErrorOccurredException(e);
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
    public SponsorUpdateResponse sponsorPutRequest(@PathVariable(name = "name") String name, @RequestBody Sponsor sponsorModel) throws SponsorNotFoundException {
        Sponsor sponsor = service.updateSponsorByName(name, sponsorModel);
        return new SponsorUpdateResponse(HttpStatus.OK, sponsor, "Sponsor was updated successfully!", Calendar.getInstance().getTime().getTime());
    }
    @DeleteMapping("/{name}")
    public SponsorDeleteRequestResponse sponsorDeleteRequest(@PathVariable(name = "name") String name) throws SponsorNotFoundException {
        Sponsor deletedSponsor = service.deleteSponsorByName(name);
        return new SponsorDeleteRequestResponse(HttpStatus.OK, deletedSponsor, "Sponsor deleted successfully!", Calendar.getInstance().getTime().getTime());
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }
}
