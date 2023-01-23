package com.univ.backend.controllers;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.response.TeamPostRequestResponse;
import com.univ.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class MainController {

    @Autowired
    public TeamService service;

    @GetMapping("/")
    public String initialEndpoint() {
        return "Hello world!";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/team")
    public TeamPostRequestResponse postTeamDetails(@RequestBody TeamEntity teamDetails) throws MandatoryFieldFoundEmptyException {
        return service.addTeam(teamDetails);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/team")
    public List<TeamEntity> getTeamDetails(@RequestParam(name = "url", required = false) String url) throws TeamMemberNotFoundException {
        if (url == null) {
            return service.getTeams();
        }
        return List.of(service.getTeamByUrl(url));
    }

}
