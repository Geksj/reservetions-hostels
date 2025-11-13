package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.exception.AlreadyExistsException;
import com.project.reservations_hotel.exception.RoomAlreadyBookedException;
import com.project.reservations_hotel.model.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFound(EntityNotFoundException e) {
        return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> conflict(AlreadyExistsException e) {
        return createResponse(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(RoomAlreadyBookedException.class)
    public ResponseEntity<ExceptionResponse> unprocessableEntity(RoomAlreadyBookedException e) {
        return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleValidateExceptions(ConstraintViolationException e) {
        return createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllUnhandledExceptions(Exception e) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ExceptionResponse> createResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(ExceptionResponse.builder()
                        .statusCode(String.valueOf(status))
                        .message(message)
                        .timestamp(Instant.now())
                        .build());
    }
}
