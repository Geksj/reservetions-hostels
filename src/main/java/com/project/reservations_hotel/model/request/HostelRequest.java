package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostelRequest {

    @NotBlank
    @Length(min = 4, max = 20)
    private String title;

    @NotBlank
    @Length(min = 6, max = 30)
    private String adTitle;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotNull
    @PositiveOrZero
    private int distanceFromCenter;

}
