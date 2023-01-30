package com.univ.backend.services;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.Brand;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.models.ImageData;
import com.univ.backend.repositories.BrandRepository;
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
public class BrandServiceImpl implements BrandService {
    @Autowired
    private FileService fileService;

    @Autowired
    private BrandRepository repository;

    @Override
    public PostRequestResponse<Brand> addBrand(Brand sponsor, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException {
        if (image != null && sponsor.getName() != null && sponsor.getDetails() != null) {
            ImageData imageData = fileService.uploadImage(Constant.IMAGE_BASE_URL, image);
            sponsor.setImage(imageData);
            Brand saved = repository.save(sponsor);
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
    public List<Brand> getAllBrands() {
        return repository.findAll();
    }

    @Override
    public List<Brand> getBrandByName(String name) throws SponsorNotFoundException {
        Optional<Brand> optionalBrand = repository.findByName(name);
        if(optionalBrand.isPresent()) {
            return List.of(optionalBrand.get());
        }
        throw new SponsorNotFoundException("No Sponsor with name " + name + " was found in database!");
    }

    // To be changed
    @Override
    public Brand updateBrandById(Long id, MultipartFile image, String sName, String details, String url) throws SponsorNotFoundException, IOException, ImageFormatException {
        Optional<Brand> optionalSponsor = repository.findById(id);
        if(optionalSponsor.isEmpty()) {
            throw new SponsorNotFoundException("No Brand with id " + id + " found in database!");
        }
        Brand sponsor = optionalSponsor.get();
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
    public Brand deleteBrandById(Long id) throws SponsorNotFoundException, FileNotFoundException {
        Optional<Brand> optionalToBeDeletedBrand = repository.findById(id);
        if(optionalToBeDeletedBrand.isEmpty()) {
            throw new SponsorNotFoundException("No Sponsor with id " + id + " found in the database!");
        }
        Brand toBeDeletedBrand = optionalToBeDeletedBrand.get();
        repository.delete(toBeDeletedBrand);
        fileService.deleteImageUsingPath(toBeDeletedBrand.getImage().getPath(), toBeDeletedBrand.getImage().getName());
        return toBeDeletedBrand;
    }
}
