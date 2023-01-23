package com.univ.backend.services;

import com.univ.backend.entities.Admin;
import com.univ.backend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAdminList(String token) {
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }
}
