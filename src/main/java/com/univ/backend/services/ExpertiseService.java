package com.univ.backend.services;

import com.univ.backend.entities.Expertise;
import com.univ.backend.exceptions.ExpertiseNotFoundException;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ExpertiseService {
    List<Expertise> getAllExpertise();

    Expertise getExpertiseByName(String name) throws ExpertiseNotFoundException;

    Expertise postExpertiseWithDetails(Expertise expertise, MultipartFile bg) throws IOException, ImageFormatException, MandatoryFieldFoundEmptyException;

    Expertise updateExpertiseUsingId(Expertise expertise, MultipartFile bg, Long id) throws ExpertiseNotFoundException, IOException, ImageFormatException;

    Expertise deleteSponsorById(Long valueOf) throws ExpertiseNotFoundException, FileNotFoundException;
}
