package com.univ.backend.controllers;

import com.univ.backend.entities.Clients;
import com.univ.backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    public ClientService service;

    @GetMapping("/")
    public String initialEndpoint() {
        return "Hello world!";
    }

    public List<Clients> getListOfClients() {
        return service.getListOfClients();
    }
}
