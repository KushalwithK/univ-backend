package com.univ.backend.services;

import com.univ.backend.entities.Brand;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.SponsorNotFoundException;
import com.univ.backend.response.PostRequestResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BrandService {
    PostRequestResponse<Brand> addBrand(Brand sponsor, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException;

    List<Brand> getAllBrands();

    List<Brand> getBrandByName(String name) throws SponsorNotFoundException;

    Brand updateBrandById(Long name, MultipartFile image, String sName, String details, String url) throws SponsorNotFoundException, IOException, ImageFormatException;

    Brand deleteBrandById(Long name) throws SponsorNotFoundException, FileNotFoundException;
}
