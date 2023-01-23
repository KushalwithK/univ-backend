package com.univ.backend.controllers;

import com.univ.backend.entities.Admin;
import com.univ.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    public List<Admin> getAdminListEndpoint(@RequestHeader(name = "authorization", required = false) String token) {
        return adminService.getAdminList(token);
    }
}
