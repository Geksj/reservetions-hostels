package com.project.reservations_hotel.model.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    @Length(min = 3, max = 8)
    private String username;

    @NotBlank
    @Length(min = 6)
    private String password;

}
