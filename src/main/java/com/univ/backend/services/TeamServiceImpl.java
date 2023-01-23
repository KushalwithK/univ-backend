package com.univ.backend.services;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.repositories.TeamRepository;
import com.univ.backend.response.TeamPutRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    public TeamRepository repository;

    @Override
    public TeamPutRequestResponse addTeam(TeamEntity entity) throws MandatoryFieldFoundEmptyException {
        if (entity.getName() != null && entity.getRole() != null && entity.getInfo() != null && entity.getImage() != null && entity.getImage() != null) {
            TeamEntity saved = repository.save(entity);
            return new TeamPutRequestResponse(
                    HttpStatus.OK,
                    saved,
                    entity.getName() + " posted in database successfully!",
                    Calendar.getInstance().getTimeInMillis()
            );
        }
        throw new MandatoryFieldFoundEmptyException("Mandatory fields must be filled!");
    }

    @Override
    public List<TeamEntity> getTeams() {
        return repository.findAll();
    }

    @Override
    public TeamEntity getTeamByUrl(String url) throws TeamMemberNotFoundException {
        Optional<TeamEntity> optionalTeam = repository.findByUrl(url);
        if (optionalTeam.isEmpty()) {
            throw new TeamMemberNotFoundException("Team member with URL " + url + " not found!");
        }
        return optionalTeam.get();
    }

    @Override
    public TeamEntity updateTeamDetails(TeamEntity teamModel, Long id) throws TeamMemberNotFoundException {
        Optional<TeamEntity> optionalTeamMember = repository.findById(id);
        if(optionalTeamMember.isEmpty()) {
            throw new TeamMemberNotFoundException("No team member with id " + id + " is not available!");
        }
        TeamEntity teamMember = optionalTeamMember.get();
        if(teamModel.getName() != null && !"".equalsIgnoreCase(teamModel.getName())) {
            teamMember.setName(teamModel.getName());
        }
        if(teamModel.getRole() != null && !"".equalsIgnoreCase(teamModel.getRole())) {
            teamMember.setRole(teamModel.getRole());
        }
        if(teamModel.getInfo() != null && !"".equalsIgnoreCase(teamModel.getInfo())) {
            teamMember.setInfo(teamModel.getInfo());
        }
        if(teamModel.getImage() != null && !"".equalsIgnoreCase(teamModel.getImage())) {
            teamMember.setImage(teamModel.getImage());
        }
        if(teamModel.getUrl() != null && !"".equalsIgnoreCase(teamModel.getUrl())) {
            teamMember.setUrl(teamModel.getUrl());
        }
        repository.save(teamMember);
        return teamMember;
    }

    @Override
    public TeamEntity deleteTeamMemberById(Long id) throws TeamMemberNotFoundException {
        Optional<TeamEntity> optionalDeletedEntity = repository.findById(id);
        if(optionalDeletedEntity.isPresent()) {
            TeamEntity deletedEntity = optionalDeletedEntity.get();
            repository.deleteById(id);
            return deletedEntity;
        }
        throw new TeamMemberNotFoundException("No team member with id " + id + " is available!");
    }
}
