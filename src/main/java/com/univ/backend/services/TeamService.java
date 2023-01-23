package com.univ.backend.services;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.response.TeamPostRequestResponse;

import java.util.List;

public interface TeamService {

    TeamPostRequestResponse addTeam(TeamEntity entity) throws MandatoryFieldFoundEmptyException;

    List<TeamEntity> getTeams();

    TeamEntity getTeamByUrl(String url) throws TeamMemberNotFoundException;
}
