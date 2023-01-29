package com.univ.backend.controllers;

import com.univ.backend.entities.Admin;
import com.univ.backend.exceptions.AdminNotFoundException;
import com.univ.backend.exceptions.IncorrectAdminDataException;
import com.univ.backend.response.AdminLoginPostResponse;
import com.univ.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAdminListEndpoint(
            @RequestParam("username") String adminUserName,
            @RequestParam("password") String adminPassword
    ) throws AdminNotFoundException, IncorrectAdminDataException {
        assert (adminUserName != null && adminPassword != null) : "Admin username and password cannot be null.";
        if(adminService.verifyAdmin(adminUserName, adminPassword)) {
            return adminService.getAdminList();
        } else {
            throw new IncorrectAdminDataException("The provided admin data was incorrect!", new Admin(adminUserName, adminPassword));
        }
    }

    @PostMapping("/login")
    public AdminLoginPostResponse postAdminPostRequest(@RequestBody Admin admin) throws AdminNotFoundException, IncorrectAdminDataException {
        if(admin != null) {
            Admin postedAdmin = adminService.verifyAdminRequest(admin);
            return new AdminLoginPostResponse(
                    HttpStatus.OK,
                    postedAdmin,
                    postedAdmin.getUserName() + " was a valid admin!",
                    Calendar.getInstance().getTime().getTime()
            );
        }
        throw new IllegalArgumentException("Please provide with the admin details!");
    }
}
