package com.univ.backend.services;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.Expertise;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ExpertiseNotFoundException;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertiseServiceImpl implements ExpertiseService {

    @Autowired
    private ExpertiseRepository repository;

    @Autowired
    private FileService fileService;

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
    public Expertise postExpertiseWithDetails(Expertise expertise, MultipartFile bg) {
        return repository.save(expertise);
    }

    @Override
    public Expertise updateExpertiseUsingId(Expertise expertise, MultipartFile bg, Long id) throws ExpertiseNotFoundException, IOException, ImageFormatException {
        Optional<Expertise> optionalExpertise = repository.findById(id);
        if(optionalExpertise.isEmpty()) {
            throw new ExpertiseNotFoundException("No Sponsor with id " + id + " not found in database!");
        }
        Expertise getExpertise = optionalExpertise.get();
        if(expertise.getName() != null && !"".equalsIgnoreCase(expertise.getName())) {
            getExpertise.setName(expertise.getName());
        }
        if(bg != null) {
            fileService.deleteImageUsingPath(getExpertise.getBg().getPath(), getExpertise.getBg().getName());
            getExpertise.setBg(fileService.uploadImage(Constant.IMAGE_BASE_URL, bg));
        }
        if(expertise.getInfo() != null && !"".equalsIgnoreCase(expertise.getInfo())) {
            getExpertise.setInfo(expertise.getInfo());
        }
        repository.save(getExpertise);
        return getExpertise;
    }

    @Override
    public Expertise deleteSponsorById(Long id) throws ExpertiseNotFoundException, FileNotFoundException {
        Optional<Expertise> optionalToBeDeletedExpertise = repository.findById(id);
        if(optionalToBeDeletedExpertise.isEmpty()) {
            throw new ExpertiseNotFoundException("No Sponsor with id " + id + " found in the database!");
        }
        Expertise toBeDeletedExpertise = optionalToBeDeletedExpertise.get();
        repository.delete(toBeDeletedExpertise);
        fileService.deleteImageUsingPath(toBeDeletedExpertise.getBg().getPath(), toBeDeletedExpertise.getBg().getName());
        return toBeDeletedExpertise;
    }
}
