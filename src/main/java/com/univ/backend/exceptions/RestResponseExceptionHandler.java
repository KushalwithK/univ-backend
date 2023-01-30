package com.univ.backend.exceptions;

import com.univ.backend.response.*;
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
    public ResponseEntity<TeamPutRequestResponse> mandatoryFieldFoundEmptyExceptionHandler(MandatoryFieldFoundEmptyException exception, WebRequest request) {
        TeamPutRequestResponse response =
                new TeamPutRequestResponse(
                        HttpStatus.BAD_REQUEST,
                        null,
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(SponsorNotFoundException.class)
    public ResponseEntity<GetRequestResponse> sponsorNotFoundException(SponsorNotFoundException exception, WebRequest request) {
        GetRequestResponse response =
                new GetRequestResponse(
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(UnexpectedServerErrorOccurredException.class)
    public ResponseEntity<UnexpectedServerErrorResponse> unexpectedServerErrorOccurred(UnexpectedServerErrorOccurredException exception, WebRequest request) {
        UnexpectedServerErrorResponse response =
                new UnexpectedServerErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage(),
                        request.getContextPath(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(ImageFormatException.class)
    public ResponseEntity<ImageFormatResponse> imageFormatExceptionHandlers(ImageFormatException exception, WebRequest request) {
        ImageFormatResponse response =
                new ImageFormatResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), Calendar.getInstance().getTime().getTime());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(IncorrectAdminDataException.class)
    public ResponseEntity<AdminLoginPostResponse> incorrectAdminDataExceptionHandler(IncorrectAdminDataException exception, WebRequest request) {
        AdminLoginPostResponse response =
                new AdminLoginPostResponse(
                        HttpStatus.BAD_REQUEST,
                        exception.getAdmin(),
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<AdminLoginPostResponse> adminNotFoundException(AdminNotFoundException exception, WebRequest request) {
        AdminLoginPostResponse response =
                new AdminLoginPostResponse(
                        HttpStatus.BAD_REQUEST,
                        exception.getAdmin(),
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ImageAlreadyExistsException.class)
    public ResponseEntity<ImageAlreadyExistsResponse> imageAlreadyExistsExceptionHandler(ImageAlreadyExistsException exception, WebRequest request) {
        ImageAlreadyExistsResponse response =
                new ImageAlreadyExistsResponse(
                        HttpStatus.BAD_REQUEST,
                        exception.getFile(),
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedExceptionResponse> unauthorizedExceptionHandler(UnauthorizedException exception, WebRequest request) {
        UnauthorizedExceptionResponse response =
                new UnauthorizedExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), Calendar.getInstance().getTime().getTime());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(ExpertiseNotFoundException.class)
    public ResponseEntity<ExpertiseNotFoundResponse> expertiseNotFoundException(SponsorNotFoundException exception, WebRequest request) {
        ExpertiseNotFoundResponse response =
                new ExpertiseNotFoundResponse(
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        Calendar.getInstance().getTime().getTime()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
