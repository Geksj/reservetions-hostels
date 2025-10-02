package com.project.reservations_hotel.controller;

import com.project.reservations_hotel.service.StatisticService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
@Slf4j
public class StatController {

    private final StatisticService statisticService;

    @GetMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> downloadCreateUser(HttpServletResponse response) {
        Path path = Path.of(statisticService.downloadUserStats());
        Resource fileResource = new FileSystemResource(path);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attached: filename=\"" + path.getFileName().toString() + "\"");

        return ResponseEntity.ok()
                .header(httpHeaders.toString())
                .contentLength(path.toFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResource);
    }

    @GetMapping("/booking")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<Resource> downloadBooking() {
        Path path = Path.of(statisticService.downloadBookingStats());
        Resource fileResource = new FileSystemResource(path);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attached: filename=\"" + path.getFileName().toString() + "\"");

        return ResponseEntity.ok()
                .header(httpHeaders.toString())
                .contentLength(path.toFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResource);
    }

}
