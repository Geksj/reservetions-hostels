package com.project.reservations_hotel.model.request;

import com.project.reservations_hotel.entity.RoleType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Length(min = 4, max = 10,
            message = "Минимальная длина для username 4, а максимальная 10")
    private String username;

    @Email(regexp = "^[A-Za-z0-9_.-]+@[A-Za-z.-]+\\\\.[A-Za-z]{2,}$",
    message = "Неккоректный email")
    private String email;

    @Length(min = 4, max = 20,
            message = "Минимальная длина для password 4, а максимальная 10")
    private String password;

    private Set<RoleType> roles;
}
