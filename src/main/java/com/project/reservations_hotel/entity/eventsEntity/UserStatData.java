package com.project.reservations_hotel.entity.eventsEntity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usersStatic")
public class UserStatData {

    @Id
    @CsvIgnore
    private String id;

    @CsvBindByName(column = "User Id", required = true)
    private Long userId;

    @CsvBindByName(column = "Created date", required = true)
    @CsvDate("dd/MM/yyyy")
    private LocalDate createdDate;

}
