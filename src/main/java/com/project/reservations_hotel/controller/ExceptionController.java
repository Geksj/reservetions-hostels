package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.exception.AlreadyExistsException;
import com.project.reservations_hotel.exception.RoomAlreadyBookedException;
import com.project.reservations_hotel.model.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@ConditionalOnProperty("${app.exception.enable}")
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .statusCode(String.valueOf(HttpStatus.NOT_FOUND))
                        .message(e.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> conflict(AlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
                        .statusCode(String.valueOf(HttpStatus.CONFLICT))
                        .message(e.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(RoomAlreadyBookedException.class)
    public ResponseEntity<ExceptionResponse> unprocessableEntity(RoomAlreadyBookedException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ExceptionResponse.builder()
                        .statusCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY))
                        .message(e.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllUnhandledExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .statusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                        .message("An unexpected error occurred")
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleValidateExceptions(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .statusCode(String.valueOf(HttpStatus.BAD_REQUEST))
                        .message(ex.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }
}
