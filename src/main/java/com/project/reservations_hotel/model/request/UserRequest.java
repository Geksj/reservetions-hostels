package com.project.reservations_hotel.model.request;

import com.project.reservations_hotel.entity.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    //@Email(regexp = "^[A-Za-z0-9_.-]@[A-Za-z.-].[A-Za-z]{2,}$")
    private String email;

    @NotBlank
    private String password;

    private Set<RoleType> roles;
}
