package com.univ.backend.services;

import com.univ.backend.entities.Expertise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExpertiseService {
    List<Expertise> getAllExpertise();

    Expertise getExpertiseByName(String name);

    Expertise postExpertiseWithDetails(Expertise build, MultipartFile bg);
}
