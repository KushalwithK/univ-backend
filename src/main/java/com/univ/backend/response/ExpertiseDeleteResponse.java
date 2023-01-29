package com.univ.backend.response;

import com.univ.backend.entities.Expertise;
import com.univ.backend.entities.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertiseDeleteResponse {
    private HttpStatus status;
    private Expertise deletedResponse;
    private String message;
    private Long timeStamp;
}
