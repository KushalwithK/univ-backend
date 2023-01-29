package com.univ.backend.controllers;

import com.univ.backend.entities.Expertise;
import com.univ.backend.response.ExpertisePostRequestResponse;
import com.univ.backend.services.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expertise")
public class ExpertiseController {

    @Autowired
    private ExpertiseService service;

    @GetMapping
    public List<Expertise> getAllExpertiseList(@RequestParam(required = false) String name) {
        if (name == null) {
            return service.getAllExpertise();
        }
        return List.of(service.getExpertiseByName(name));
    }

    public ExpertisePostRequestResponse expertisePostRequestResponse(
            @RequestParam("bg") MultipartFile bg,
            @RequestParam("name") String name,
            @RequestParam("info") String info,
            @RequestParam("url") String url
    ) {
        Expertise expertise = service.postExpertiseWithDetails(
                Expertise.builder()
                        .name(name)
                        .info(info)
                        .url(url)
                        .build(),
                bg
        );
        return null;
    }
}
