package com.univ.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageFormatResponse {
    private HttpStatus status;
    private String message;
    private Long timeStamp;
}
