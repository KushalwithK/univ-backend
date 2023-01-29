package com.univ.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnauthorizedExceptionResponse {
    private HttpStatus status;
    private String message;
    private Long timeStamp;
}
