package com.univ.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnexpectedServerErrorResponse {
    private HttpStatus status;
    private String message;
    private String endpoint;
    private Long timeStamp;
}
