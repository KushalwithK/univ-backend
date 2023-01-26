package com.univ.backend.services;

import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.response.SponsorPostRequestResponse;
import com.univ.backend.response.SponsorUpdateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SponsorService {


    SponsorPostRequestResponse addSponsor(Sponsor sponsor, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException;

    List<Sponsor> getAllSponsors();

    List<Sponsor> getSponsorByName(String name) throws SponsorNotFoundException;

    Sponsor updateSponsorByName(String name, MultipartFile image, String sName, String details, String url) throws SponsorNotFoundException, IOException, ImageFormatException;

    Sponsor deleteSponsorByName(String name) throws SponsorNotFoundException, FileNotFoundException;
}
