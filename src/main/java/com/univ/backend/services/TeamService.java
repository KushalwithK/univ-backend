package com.univ.backend.services;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.response.TeamPutRequestResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TeamService {

    TeamPutRequestResponse addTeam(TeamEntity entity, MultipartFile image) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException;

    List<TeamEntity> getTeams();

    TeamEntity getTeamByUrl(String url) throws TeamMemberNotFoundException;

    TeamEntity updateTeamDetails(TeamEntity teamModel, Long id, MultipartFile image) throws TeamMemberNotFoundException, IOException, ImageFormatException;

    TeamEntity deleteTeamMemberById(Long id) throws TeamMemberNotFoundException, FileNotFoundException;
}
