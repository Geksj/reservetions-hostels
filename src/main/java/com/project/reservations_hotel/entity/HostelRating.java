package com.project.reservations_hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
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
