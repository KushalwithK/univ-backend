package com.univ.backend.services;

import com.univ.backend.entities.Admin;
import com.univ.backend.exceptions.AdminNotFoundException;
import com.univ.backend.exceptions.IncorrectAdminDataException;

import java.util.ArrayList;
import java.util.List;

public interface AdminService {

    List<Admin> getAdminList(String token);

    Admin verifyAdminRequest(Admin admin) throws IncorrectAdminDataException, AdminNotFoundException;
}
