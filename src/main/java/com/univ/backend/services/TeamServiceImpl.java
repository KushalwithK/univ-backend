package com.univ.backend.services;

import com.univ.backend.constants.Constant;
import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.models.ImageData;
import com.univ.backend.repositories.TeamRepository;
import com.univ.backend.response.TeamPutRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    public TeamRepository repository;

    @Autowired
    private FileService fileService;

    @Override
    public TeamPutRequestResponse addTeam(TeamEntity entity, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException {
        if (image != null && entity.getName() != null && entity.getRole() != null && entity.getInfo() != null && entity.getUrl() != null) {
            ImageData imageData = fileService.uploadImage(Constant.IMAGE_BASE_URL, image);
            entity.setImage(imageData);
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
    public TeamEntity updateTeamDetails(TeamEntity teamModel, Long id, MultipartFile image) throws TeamMemberNotFoundException, IOException, ImageFormatException {
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
        if(image != null) {
            fileService.deleteImageUsingPath(teamMember.getImage().getPath(), teamMember.getImage().getName());
            teamMember.setImage(fileService.uploadImage(Constant.IMAGE_BASE_URL, image));
        }
        if(teamModel.getUrl() != null && !"".equalsIgnoreCase(teamModel.getUrl())) {
            teamMember.setUrl(teamModel.getUrl());
        }
        repository.save(teamMember);
        return teamMember;
    }

    @Override
    public TeamEntity deleteTeamMemberById(Long id) throws TeamMemberNotFoundException, FileNotFoundException {
        Optional<TeamEntity> optionalDeletedEntity = repository.findById(id);
        if(optionalDeletedEntity.isPresent()) {
            TeamEntity deletedEntity = optionalDeletedEntity.get();
            repository.delete(deletedEntity);
            fileService.deleteImageUsingPath(deletedEntity.getImage().getPath(), deletedEntity.getImage().getName());
            return deletedEntity;
        }
        throw new TeamMemberNotFoundException("No team member with id " + id + " is available!");
    }
}
