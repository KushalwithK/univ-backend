package com.univ.backend.services;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.response.AdminPostRequestResponse;

import java.util.List;

public interface TeamService {

    AdminPostRequestResponse<TeamEntity> addTeam(TeamEntity entity);

    List<TeamEntity> getTeams();

    TeamEntity getTeamByUrl(String url);
}
