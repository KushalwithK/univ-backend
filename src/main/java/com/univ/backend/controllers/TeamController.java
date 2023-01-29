package com.univ.backend.controllers;

import com.univ.backend.entities.Admin;
import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.*;
import com.univ.backend.response.TeamDeleteRequestResponse;
import com.univ.backend.response.TeamMemberUpdateResponse;
import com.univ.backend.response.TeamPutRequestResponse;
import com.univ.backend.services.AdminService;
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
@RequestMapping("/api/v1/team")
//@CrossOrigin(origins = "https://localhost:5173")
public class TeamController {

    @Autowired
    public TeamService service;

    @Autowired
    private AdminService adminService;

    @PostMapping
    public TeamPutRequestResponse postTeamDetails(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            @RequestParam("info") String info,
            @RequestParam("url") String url,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws MandatoryFieldFoundEmptyException, IOException, ImageFormatException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            return service.addTeam(
                    TeamEntity.builder()
                            .name(name)
                            .role(role)
                            .info(info)
                            .url(url)
                            .build(),
                    image
            );
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @GetMapping
    public List<TeamEntity> getTeamDetails(@RequestParam(name = "url", required = false) String url) throws TeamMemberNotFoundException {
        if (url == null) {
            return service.getTeams();
        }
        return List.of(service.getTeamByUrl(url));
    }

    @PutMapping
    public TeamMemberUpdateResponse teamMemberDetailPutRequest(
            @RequestParam(name = "id") String id,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "info", required = false) String info,
            @RequestParam(value = "url", required = false) String url,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws TeamMemberNotFoundException, IOException, ImageFormatException, IncorrectAdminDataException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            TeamEntity teamMember = service.updateTeamDetails(
                    TeamEntity.builder()
                            .name(name)
                            .role(role)
                            .info(info)
                            .url(url)
                            .build(),
                    Long.valueOf(id),
                    image);
            return new TeamMemberUpdateResponse(
                    HttpStatus.OK,
                    teamMember,
                    teamMember.getId() + " was updated successfully!",
                    Calendar.getInstance().getTime().getTime());
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

    @DeleteMapping
    public TeamDeleteRequestResponse teamMemberDeleteRequest(
            @RequestParam(name = "id") String id,
            @RequestHeader(value = "username", required = false) String adminUserName,
            @RequestHeader(value = "password", required = false) String adminPassword
    ) throws TeamMemberNotFoundException, FileNotFoundException, IncorrectAdminDataException, UnauthorizedException {
        if (adminUserName == null || adminPassword == null) {
            throw new UnauthorizedException("Unauthorized access.");
        }
        if (adminService.verifyAdmin(adminUserName, adminPassword)) {
            TeamEntity deletedEntity = service.deleteTeamMemberById(Long.valueOf(id));
            return new TeamDeleteRequestResponse(
                    HttpStatus.OK,
                    deletedEntity,
                    "Member " + id + " deleted successfully!",
                    Calendar.getInstance().getTime().getTime()
            );
        } else {
            throw new UnauthorizedException("The provided admin data was incorrect!");
        }
    }

}
