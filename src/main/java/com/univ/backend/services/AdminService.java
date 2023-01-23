package com.univ.backend.services;

import com.univ.backend.entities.Admin;

import java.util.ArrayList;
import java.util.List;

public interface AdminService {

    List<Admin> getAdminList(String token);

}
