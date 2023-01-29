package com.univ.backend.services;

import com.univ.backend.entities.Expertise;
import com.univ.backend.repositories.ExpertiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertiseControllerImpl implements ExpertiseService {

    private ExpertiseRepository repository;
    @Override
    public List<Expertise> getAllExpertise() {
        return repository.findAll();
    }

    @Override
    public Expertise getExpertiseByName(String name) {
        Assert.notNull(name, "Name cannot be null!");
        Optional<Expertise> optionalExpertise = repository.findByName(name);
        return optionalExpertise.orElse(null);
    }

    @Override
    public Expertise postExpertiseWithDetails(Expertise build, MultipartFile bg) {
        return null;
    }
}
