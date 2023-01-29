package com.univ.backend.response;

import com.univ.backend.models.ImageData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAlreadyExistsResponse {

    private HttpStatus status;
    private String name;
    private String message;
    private Long timeStamp;
}
