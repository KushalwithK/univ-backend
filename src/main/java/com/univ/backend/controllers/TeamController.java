package com.univ.backend.controllers;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.ImageFormatException;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.response.TeamDeleteRequestResponse;
import com.univ.backend.response.TeamMemberUpdateResponse;
import com.univ.backend.response.TeamPutRequestResponse;
import com.univ.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;



@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "https://localhost:5173")
public class TeamController {

    @Autowired
    public TeamService service;

    @PostMapping("/team")
    public TeamPutRequestResponse postTeamDetails(@RequestParam("image") MultipartFile image, @RequestParam("name") String name, @RequestParam("role") String role, @RequestParam("info") String info, @RequestParam("url") String url) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException {
        return service.addTeam(
                TeamEntity.builder()
                        .name(name)
                        .role(role)
                        .info(info)
                        .url(url)
                        .build(),
                image
        );
    }

    @GetMapping("/team")
    public List<TeamEntity> getTeamDetails(@RequestParam(name = "url", required = false) String url) throws TeamMemberNotFoundException {
        if (url == null) {
            return service.getTeams();
        }
        return List.of(service.getTeamByUrl(url));
    }

    @PutMapping("/team/{name}")
    public TeamMemberUpdateResponse teamMemberDetailPutRequest(
            @PathVariable(name = "name") String fetchName,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "info", required = false) String info,
            @RequestParam(value = "url", required = false) String url
    ) throws TeamMemberNotFoundException, IOException, ImageFormatException {
        TeamEntity teamMember = service.updateTeamDetails(
                TeamEntity.builder()
                        .name(name)
                        .role(role)
                        .info(info)
                        .url(url)
                        .build(),
                fetchName,
                image);
        return new TeamMemberUpdateResponse(
                HttpStatus.OK,
                teamMember,
                teamMember.getId() + " was updated successfully!",
                Calendar.getInstance().getTime().getTime());
    }

    @DeleteMapping("/team/{name}")
    public TeamDeleteRequestResponse teamMemberDeleteRequest(@PathVariable(name = "name") String name) throws TeamMemberNotFoundException, FileNotFoundException {
        TeamEntity deletedEntity = service.deleteTeamMemberById(name);
        return new TeamDeleteRequestResponse(HttpStatus.OK, deletedEntity, "Member " + name + " deleted successfully!", Calendar.getInstance().getTime().getTime());
    }

}
