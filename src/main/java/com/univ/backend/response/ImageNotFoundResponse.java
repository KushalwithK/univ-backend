package com.univ.backend.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageNotFoundResponse {
    private HttpStatus status;
    private String message;
    private Long timeStamp;
}
