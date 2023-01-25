package com.univ.backend.response;

import com.univ.backend.entities.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorPostRequestResponse {
    private HttpStatus status;
    private Sponsor postedData;
    private String message;
    private Long timeStamp;
}
