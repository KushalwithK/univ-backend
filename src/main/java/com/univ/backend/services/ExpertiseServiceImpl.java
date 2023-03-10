package com.univ.backend.services;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.Expertise;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ExpertiseNotFoundException;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.models.ImageData;
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
    public Expertise getExpertiseByName(String name) throws ExpertiseNotFoundException {
        Optional<Expertise> optionalExpertise = repository.findByName(name);
        if(optionalExpertise.isPresent()) {
            return optionalExpertise.get();
        }
        throw new ExpertiseNotFoundException("No Expertise with name " + name + " was found in database!");
    }

    @Override
    public Expertise postExpertiseWithDetails(Expertise expertise, MultipartFile bg) throws IOException, ImageFormatException, MandatoryFieldFoundEmptyException {
        if (bg != null && expertise.getName() != null && expertise.getInfo() != null && expertise.getUrl() != null) {
            ImageData imageData = fileService.uploadImage(Constant.IMAGE_BASE_URL, bg);
            expertise.setBg(imageData);
            Expertise saved = repository.save(expertise);
            return saved;
        }
        throw new MandatoryFieldFoundEmptyException("Mandatory fields ( name, details and image ) must be filled!");
    }

    @Override
    public Expertise updateExpertiseUsingId(Expertise expertise, MultipartFile bg, Long id) throws ExpertiseNotFoundException, IOException, ImageFormatException {
        Optional<Expertise> optionalExpertise = repository.findById(id);
        if(optionalExpertise.isEmpty()) {
            throw new ExpertiseNotFoundException("No Expertise with id " + id + " not found in database!");
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
            throw new ExpertiseNotFoundException("No Expertise with id " + id + " found in the database!");
        }
        Expertise toBeDeletedExpertise = optionalToBeDeletedExpertise.get();
        repository.delete(toBeDeletedExpertise);
        fileService.deleteImageUsingPath(toBeDeletedExpertise.getBg().getPath(), toBeDeletedExpertise.getBg().getName());
        return toBeDeletedExpertise;
    }
}
