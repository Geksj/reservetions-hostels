package com.project.reservations_hotel.mapper;

import com.project.reservations_hotel.entity.User;
import com.project.reservations_hotel.model.request.UserRequest;
import com.project.reservations_hotel.model.response.UserListResponse;
import com.project.reservations_hotel.model.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User requestToUser(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();
    }

    public User requestToUser(Long id, UserRequest request) {
        User user = requestToUser(request);

        user.setId(id);

        return user;
    }

    public UserResponse userToResponse(User user) {
        List<String> roles = user.getRoles().stream()
                .map(Enum::toString)
                .toList();

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserListResponse userListToListResponse(List<User> users) {
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream()
                .map(this::userToResponse)
                .toList()
        );

        return response;
    }
}
