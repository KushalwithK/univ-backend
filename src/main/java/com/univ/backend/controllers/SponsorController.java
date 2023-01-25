package com.univ.backend.controllers;

import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.response.*;
import com.univ.backend.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class SponsorController {

    @Autowired
    private SponsorService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/sponsor")
    public SponsorPostRequestResponse sponsorPostRequest(@RequestBody Sponsor sponsor) throws MandatoryFieldFoundEmptyException {
        return service.addSponsor(sponsor);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/sponsor")
    public List<Sponsor> sponsorGetRequest(@RequestParam(name = "name", required = false) String name) throws SponsorNotFoundException {
        if (name == null) {
            return service.getAllSponsors();
        }
        return service.getSponsorByName(name);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/sponsor/{name}")
    public SponsorUpdateResponse sponsorPutRequest(@PathVariable(name = "name") String name, @RequestBody Sponsor sponsorModel) throws SponsorNotFoundException {
        Sponsor sponsor = service.updateSponsorByName(name, sponsorModel);
        return new SponsorUpdateResponse(HttpStatus.OK, sponsor, "Sponsor was updated successfully!", Calendar.getInstance().getTime().getTime());
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/sponsor/{name}")
    public SponsorDeleteRequestResponse sponsorDeleteRequest(@PathVariable(name = "name") String name) throws SponsorNotFoundException {
        Sponsor deletedSponsor = service.deleteSponsorByName(name);
        return new SponsorDeleteRequestResponse(HttpStatus.OK, deletedSponsor, "Sponsor deleted successfully!", Calendar.getInstance().getTime().getTime());
    }
}
