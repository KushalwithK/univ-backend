package com.univ.backend.response;

import com.univ.backend.entities.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPostRequestResponse {
    private HttpStatus status;
    private TeamEntity data;
    private String message;
    private Long timeStamp;
}
