package com.univ.backend.exceptions;

import com.univ.backend.response.TeamMemberGetResponse;
import com.univ.backend.response.TeamPostRequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Calendar;

@ControllerAdvice
@ResponseStatus
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TeamMemberNotFoundException.class)
    public ResponseEntity<TeamMemberGetResponse> teamMemberNotFoundResponse(TeamMemberNotFoundException exception, WebRequest request) {
        TeamMemberGetResponse response =
                new TeamMemberGetResponse(
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MandatoryFieldFoundEmptyException.class)
    public ResponseEntity<TeamPostRequestResponse> mandatoryFieldFoundEmptyExceptionHandler(MandatoryFieldFoundEmptyException exception, WebRequest request) {
        TeamPostRequestResponse response =
                new TeamPostRequestResponse(
                        HttpStatus.BAD_REQUEST,
                        null,
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
