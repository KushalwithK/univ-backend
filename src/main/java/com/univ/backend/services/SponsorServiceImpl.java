package com.univ.backend.services;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.models.ImageData;
import com.univ.backend.repositories.ImageDataRepository;
import com.univ.backend.repositories.SponsorRepository;
import com.univ.backend.response.PostRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorServiceImpl implements SponsorService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Autowired
    private SponsorRepository repository;

    @Autowired
    private FileService fileService;
    @Override
    public PostRequestResponse<Sponsor> addSponsor(Sponsor sponsor, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException {
        if (image != null && sponsor.getName() != null && sponsor.getDetails() != null) {
            ImageData imageData = fileService.uploadImage(Constant.IMAGE_BASE_URL, image);
            sponsor.setImage(imageData);
            Sponsor saved = repository.save(sponsor);
            return new PostRequestResponse<>(
                    HttpStatus.OK,
                    saved,
                    sponsor.getName() + " posted in database successfully!",
                    Calendar.getInstance().getTimeInMillis()
            );
        }
        throw new MandatoryFieldFoundEmptyException("Mandatory fields ( name, details and image ) must be filled!");
    }

    @Override
    public List<Sponsor> getAllSponsors() {
        return repository.findAll();
    }

    @Override
    public List<Sponsor> getSponsorByName(String name) throws SponsorNotFoundException {
        Optional<Sponsor> optionalSponsor = repository.findByName(name);
        if(optionalSponsor.isPresent()) {
            return List.of(optionalSponsor.get());
        }
        throw new SponsorNotFoundException("No Client with name " + name + " was found in database!");
    }

    // To be changed
    @Override
    public Sponsor updateSponsorById(Long id, MultipartFile image, String sName, String details, String url) throws SponsorNotFoundException, IOException, ImageFormatException {
        Optional<Sponsor> optionalSponsor = repository.findById(id);
        if(optionalSponsor.isEmpty()) {
            throw new SponsorNotFoundException("No Client with id " + id + " not found in database!");
        }
        Sponsor sponsor = optionalSponsor.get();
        if(sName != null && !"".equalsIgnoreCase(sName)) {
            sponsor.setName(sName);
        }
        if(image != null) {
            fileService.deleteImageUsingPath(sponsor.getImage().getPath(), sponsor.getImage().getName());
            sponsor.setImage(fileService.uploadImage(Constant.IMAGE_BASE_URL, image));
        }
        if(details != null && !"".equalsIgnoreCase(details)) {
            sponsor.setDetails(details);
        }
        repository.save(sponsor);
        return sponsor;
    }

    @Override
    public Sponsor deleteSponsorById(Long id) throws SponsorNotFoundException, FileNotFoundException {
        Optional<Sponsor> optionalToBeDeletedSponsor = repository.findById(id);
        if(optionalToBeDeletedSponsor.isEmpty()) {
            throw new SponsorNotFoundException("No Client with id " + id + " found in the database!");
        }
        Sponsor toBeDeletedSponsor = optionalToBeDeletedSponsor.get();
        repository.delete(toBeDeletedSponsor);
        fileService.deleteImageUsingPath(toBeDeletedSponsor.getImage().getPath(), toBeDeletedSponsor.getImage().getName());
        return toBeDeletedSponsor;
    }
}
