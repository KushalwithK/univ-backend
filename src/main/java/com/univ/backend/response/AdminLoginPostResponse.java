package com.univ.backend.response;

import com.univ.backend.entities.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginPostResponse {
    public HttpStatus status;
    public Admin adminDetails;
    public String message;
    public Long timeStamp;
}
