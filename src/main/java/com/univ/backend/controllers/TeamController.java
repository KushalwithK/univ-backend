package com.univ.backend.controllers;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.response.TeamDeleteRequestResponse;
import com.univ.backend.response.TeamMemberUpdateResponse;
import com.univ.backend.response.TeamPutRequestResponse;
import com.univ.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "https://localhost:5173")
public class TeamController {

    @Autowired
    public TeamService service;

    @PostMapping("/team")
    public TeamPutRequestResponse postTeamDetails(@RequestBody TeamEntity teamDetails) throws MandatoryFieldFoundEmptyException {
        return service.addTeam(teamDetails);
    }

    @GetMapping("/team")
    public List<TeamEntity> getTeamDetails(@RequestParam(name = "url", required = false) String url) throws TeamMemberNotFoundException {
        if (url == null) {
            return service.getTeams();
        }
        return List.of(service.getTeamByUrl(url));
    }

    @PutMapping("/team/{id}")
    public TeamMemberUpdateResponse teamMemberDetailPutRequest(@PathVariable(name = "id") String id, @RequestBody TeamEntity teamModel) throws TeamMemberNotFoundException {
        TeamEntity teamMember = service.updateTeamDetails(teamModel, Long.valueOf(id));
        return new TeamMemberUpdateResponse(HttpStatus.OK, teamMember, teamMember.getId() + " was updated successfully!", Calendar.getInstance().getTime().getTime());
    }

    @DeleteMapping("/team/{id}")
    public TeamDeleteRequestResponse teamMemberDeleteRequest(@PathVariable(name = "id") String id) throws TeamMemberNotFoundException {
        TeamEntity deletedEntity = service.deleteTeamMemberById(Long.valueOf(id));
        return new TeamDeleteRequestResponse(HttpStatus.OK, deletedEntity, "Member " + id + " deleted successfully!", Calendar.getInstance().getTime().getTime());
    }

}
