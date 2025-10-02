package com.project.reservations_hotel.service;

import com.project.reservations_hotel.entity.User;
import com.project.reservations_hotel.exception.AlreadyExistsException;
import com.project.reservations_hotel.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found by userId: " + userId));
    }

    public User create(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("User already exists by username: " + user.getUsername());
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("Email already exists by email: " + user.getEmail());
        }

        return userRepository.save(user);
    }

    public User update(User user) {
        User existsUser = findById(user.getId());

        existsUser.setId(user.getId());
        existsUser.setUsername(user.getUsername());
        existsUser.setEmail(user.getEmail());
        existsUser.setPassword(user.getPassword());
        existsUser.setRoles(user.getRoles());

        return userRepository.save(existsUser);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

}
