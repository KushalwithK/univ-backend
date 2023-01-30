package com.univ.backend.services;

import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.response.PostRequestResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SponsorService {


    PostRequestResponse<Sponsor> addSponsor(Sponsor sponsor, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException;

    List<Sponsor> getAllSponsors();

    List<Sponsor> getSponsorByName(String name) throws SponsorNotFoundException;

    Sponsor updateSponsorById(Long name, MultipartFile image, String sName, String details, String url) throws SponsorNotFoundException, IOException, ImageFormatException;

    Sponsor deleteSponsorById(Long name) throws SponsorNotFoundException, FileNotFoundException;
}
