package com.univ.backend.response;

import com.univ.backend.entities.Expertise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertisePostRequestResponse {
    private HttpStatus status;
    private Expertise postedResponse;
    private String message;
    private Long timeStamp;
}
