package com.project.reservations_hotel.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

    @NotNull
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
    @Positive
    @Min(1)
    private Long hostelId;

}
