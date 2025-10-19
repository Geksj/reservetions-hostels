package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

    @NotBlank
    @Length(min = 4, max = 20)
    private String title;

    private String description;

    @NotNull
    @Min(1)
    private int roomNumber;

    @NotNull
    @PositiveOrZero
    private int price;

    @NotNull
    @Min(1)
    private int maxSize;

    @NotNull
    @Min(1)
    private Long hostelId;

}
