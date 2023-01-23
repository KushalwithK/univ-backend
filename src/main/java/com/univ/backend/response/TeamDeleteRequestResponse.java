package com.univ.backend.response;

import com.univ.backend.entities.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDeleteRequestResponse {
    private HttpStatus status;
    private TeamEntity deletedResponse;
    private String message;
    private Long timeStamp;
}
