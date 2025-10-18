package com.project.reservations_hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HostelRating {

    @ColumnDefault("0")
    private float rating;

    @Column(name = "rating_count")
    @ColumnDefault("0")
    private int ratingCount;

    @Transient
    private float sumRating;

    public void addRating(float ratingOfUser) {
        sumRating = rating * ratingCount + ratingOfUser;
        ratingCount++;
        rating = (float) Math.round((sumRating / ratingCount) * 10) / 10;
    }

}
