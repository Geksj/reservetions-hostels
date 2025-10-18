package com.project.reservations_hotel.utils;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AppDateUtils {

    @Value("${app.date.format}")
    private static String format;

    public static LocalDate parseStringFormat(String stringDate) {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(format));
    }

    public static Instant parseLDToInstant(LocalDate ld) {
        return ld.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }

    public static Instant parseStringToInstant(String stringDate) {
        String[] strings = stringDate.split("/");

        return Instant.parse(strings[2] + "-" + strings[1] + "-" + strings[0] + "T00:00:00Z");
    }
}
