package com.project.reservations_hotel.monitoring.controller;

import com.project.reservations_hotel.monitoring.ExportBooking;
import com.project.reservations_hotel.monitoring.ExportUser;
import com.project.reservations_hotel.monitoring.service.ExportService;
import com.project.reservations_hotel.monitoring.service.MonitoringService;
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
@RequestMapping("/api/v1/monitoring/export")
@RequiredArgsConstructor
@Slf4j
public class ExportController {

    private final MonitoringService statisticService;

    private final ExportService exportService;

    @GetMapping("/createdUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> exportFileUser() {
        Path path = Path.of(exportService.export(ExportUser.class));

        return sendFile(path);
    }

    @GetMapping("/dateBooking")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> exportFileBooking() {
        Path path = Path.of(exportService.export(ExportBooking.class));

        return sendFile(path);
    }

    private ResponseEntity<Resource> sendFile(Path path) {
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
