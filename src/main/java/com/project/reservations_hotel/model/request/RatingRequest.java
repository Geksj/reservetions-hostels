package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    @NotNull
    @Positive
    @Min(1)
    private Long hostelId;

    @NotNull
    @Min(1) @Max(5)
    @Positive
    private float ratingOfUser;

}
