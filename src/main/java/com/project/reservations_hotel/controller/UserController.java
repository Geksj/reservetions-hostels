package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.entity.User;
import com.project.reservations_hotel.mapper.UserMapper;
import com.project.reservations_hotel.model.request.UserRequest;
import com.project.reservations_hotel.model.response.UserListResponse;
import com.project.reservations_hotel.model.response.UserResponse;
import com.project.reservations_hotel.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(
                userMapper.userListToListResponse(userService.findAll())
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") @Min(1) Long userId) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(userId))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        User cretedUser = userService.create(
                userMapper.requestToUser(request)
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(cretedUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or (hasAnyRole('USER') and #userId = authentication.principal.id)")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") @Min(1) Long userId,
                                                   @RequestBody @Valid UserRequest request) {
        User user = userMapper.requestToUser(userId, request);

        return ResponseEntity.ok(
                userMapper.userToResponse(userService.update(user))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") @Min(1) Long userId) {
        userService.deleteById(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
