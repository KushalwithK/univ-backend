package com.univ.backend.services;

import com.univ.backend.entities.Clients;
import com.univ.backend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    public ClientRepository repository;
    @Override
    public List<Clients> getListOfClients() {
        return repository.findAll();
    }
}
