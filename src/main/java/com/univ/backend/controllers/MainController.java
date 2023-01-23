package com.univ.backend.controllers;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.response.AdminPostRequestResponse;
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

    @PostMapping("/team")
    public AdminPostRequestResponse<TeamEntity> postTeamDetails(@RequestBody TeamEntity teamDetails) {
        return service.addTeam(teamDetails);
    }

    @GetMapping("/team")
    public List<TeamEntity> getTeamDetails(@RequestParam(name = "url", required = false) String url) {
        if (url == null) {
            return service.getTeams();
        }
        return List.of(service.getTeamByUrl(url));
    }

}
