package com.univ.backend.services;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.repositories.TeamRepository;
import com.univ.backend.response.AdminPostRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    public TeamRepository repository;

    @Override
    public AdminPostRequestResponse<TeamEntity> addTeam(TeamEntity entity) {
        TeamEntity saved = repository.save(entity);
        return new AdminPostRequestResponse<>(200, saved);
    }

    @Override
    public List<TeamEntity> getTeams() {
        return repository.findAll();
    }

    @Override
    public TeamEntity getTeamByUrl(String url) {
        return repository.findByUrl(url);
    }
}
