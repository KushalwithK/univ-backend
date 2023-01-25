package com.univ.backend.services;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.models.ImageData;
import com.univ.backend.repositories.SponsorRepository;
import com.univ.backend.response.SponsorPostRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorServiceImpl implements SponsorService {

    @Autowired
    private SponsorRepository repository;

    @Autowired
    private FileService fileService;
    @Override
    public SponsorPostRequestResponse addSponsor(Sponsor sponsor, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException {
        if (image != null && sponsor.getName() != null && sponsor.getDetails() != null) {
            ImageData imageData = fileService.uploadImage(Constant.IMAGE_BASE_URL, image);
            sponsor.setImage(imageData);
            Sponsor saved = repository.save(sponsor);
            return new SponsorPostRequestResponse(
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
        throw new SponsorNotFoundException("No Sponsor with name " + name + " was found in database!");
    }

    // To be changed
    @Override
    public Sponsor updateSponsorByName(String name, Sponsor sponsorModel) throws SponsorNotFoundException {
        Optional<Sponsor> optionalSponsor = repository.findByName(name);
        if(optionalSponsor.isEmpty()) {
            throw new SponsorNotFoundException("No Sponsor with name " + name + " not found in database!");
        }
        Sponsor sponsor = optionalSponsor.get();
        if(sponsorModel.getName() != null && !"".equalsIgnoreCase(sponsorModel.getName())) {
            sponsor.setName(sponsorModel.getName());
        }
        if(sponsorModel.getImage() != null && !"".equalsIgnoreCase(sponsorModel.getImage().getPath())) {
            sponsor.setImage(sponsorModel.getImage());
        }
        if(sponsorModel.getDetails() != null && !"".equalsIgnoreCase(sponsorModel.getDetails())) {
            sponsor.setDetails(sponsorModel.getDetails());
        }
        repository.save(sponsor);
        return sponsor;
    }

    @Override
    public Sponsor deleteSponsorByName(String name) throws SponsorNotFoundException {
        Optional<Sponsor> optionalToBeDeletedSponsor = repository.findByName(name);
        if(optionalToBeDeletedSponsor.isEmpty()) {
            throw new SponsorNotFoundException("No Sponsor with name " + name + " found in the database!");
        }
        Sponsor toBeDeletedSponsor = optionalToBeDeletedSponsor.get();
        repository.deleteByName(name);
        return toBeDeletedSponsor;
    }
}
