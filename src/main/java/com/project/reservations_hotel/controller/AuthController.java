package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.entity.User;
import com.project.reservations_hotel.mapper.UserMapper;
import com.project.reservations_hotel.model.auth.AuthResponse;
import com.project.reservations_hotel.model.auth.LoginRequest;
import com.project.reservations_hotel.model.auth.SimpleMessage;
import com.project.reservations_hotel.model.kafka.StatEvent;
import com.project.reservations_hotel.model.request.UserRequest;
import com.project.reservations_hotel.security.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${app.kafka.userCreatedTopic}")
    private String userCreatedTopic;

    private final SecurityService securityService;

    private final UserMapper userMapper;

    private final KafkaTemplate<String, StatEvent> kafkaTemplate;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(securityService.authenticateUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<SimpleMessage> registry(@RequestBody UserRequest request) {
        User createdUser = securityService.createUser(userMapper.requestToUser(request));

        kafkaTemplate.send(userCreatedTopic , StatEvent.builder()
                .userId(createdUser.getId())
                .createdDate(Instant.now())
                .build());

        return ResponseEntity.ok(new SimpleMessage("User created"));
    }

}
