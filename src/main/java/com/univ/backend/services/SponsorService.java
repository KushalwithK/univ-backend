package com.univ.backend.services;

import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.response.SponsorPostRequestResponse;
import com.univ.backend.response.SponsorUpdateResponse;

import java.util.List;

public interface SponsorService {


    SponsorPostRequestResponse addSponsor(Sponsor sponsor) throws MandatoryFieldFoundEmptyException;

    List<Sponsor> getAllSponsors();

    List<Sponsor> getSponsorByName(String name) throws SponsorNotFoundException;

    Sponsor updateSponsorByName(String name, Sponsor sponsorModel) throws SponsorNotFoundException;

    Sponsor deleteSponsorByName(String name) throws SponsorNotFoundException;
}
