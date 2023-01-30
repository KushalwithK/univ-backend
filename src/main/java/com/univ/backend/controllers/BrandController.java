package com.univ.backend.controllers;

import com.univ.backend.entities.Brand;
import com.univ.backend.entities.Sponsor;
import com.univ.backend.exceptions.*;
import com.univ.backend.response.DeleteRequestResponse;
import com.univ.backend.response.PostRequestResponse;
import com.univ.backend.response.UpdateResponse;
import com.univ.backend.services.AdminService;
import com.univ.backend.services.BrandService;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    @Autowired
    private BrandService service;

    @Autowired
    private AdminService adminService;

    @PostMapping(consumes = "multipart/form-data")
    public PostRequestResponse<Brand> sponsorPostRequest(
            @RequestParam("image") MultipartFile image,
            @RequestParam String name,
            @RequestParam String details,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws MandatoryFieldFoundEmptyException, UnexpectedServerErrorOccurredException, ImageFormatException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            try {
                return service.addBrand(new Brand(name, details), image);
            } catch (IOException e) {
                throw new UnexpectedServerErrorOccurredException(e);
            }
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @GetMapping
    public List<Brand> sponsorGetRequest(@RequestParam(name = "name", required = false) String name) throws SponsorNotFoundException {
        if (name == null) {
            return service.getAllBrands();
        }
        return service.getBrandByName(name);
    }

    @PutMapping
    public UpdateResponse<Brand> sponsorPutRequest(
            @RequestParam(name = "id") String id,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(name = "name", required = false) String sName,
            @RequestParam(required = false) String details,
            @RequestParam(required = false) String url,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws SponsorNotFoundException, IOException, ImageFormatException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Brand brand = service.updateBrandById(Long.valueOf(id), image, sName, details, url);
            return new UpdateResponse<>(HttpStatus.OK, brand, "Sponsor was updated successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @DeleteMapping
    public DeleteRequestResponse<Brand> sponsorDeleteRequest(
            @RequestParam(name = "id") String id,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws SponsorNotFoundException, FileNotFoundException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            Brand deletedBrand = service.deleteBrandById(Long.valueOf(id));
            return new DeleteRequestResponse<>(HttpStatus.OK, deletedBrand, "Sponsor deleted successfully!", Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }
}
