package com.univ.backend.services;

import com.univ.backend.entities.Admin;
import com.univ.backend.exceptions.AdminNotFoundException;
import com.univ.backend.exceptions.IncorrectAdminDataException;
import com.univ.backend.exceptions.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;

public interface AdminService {

    List<Admin> getAdminList();

    Admin verifyAdminRequest(Admin admin) throws IncorrectAdminDataException, AdminNotFoundException;

    boolean verifyAdmin(String adminUserName, String adminPassword) throws UnauthorizedException;
}
