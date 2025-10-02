package com.project.reservations_hotel.entity.eventsEntity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookingStat")
public class BookingStatData {

    @Id
    private String id;

    @CsvBindByName(column = "User Id")
    private Long userId;

    @CsvBindByName(column = "Check in Date")
    @CsvDate("dd/MM/yyyy")
    private LocalDate checkInDate;

    @CsvBindByName(column = "Departure Date")
    @CsvDate("dd/MM/yyyy")
    private LocalDate departureDate;

}
