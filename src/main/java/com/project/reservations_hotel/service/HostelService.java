package com.project.reservations_hotel.service;

import com.project.reservations_hotel.entity.Hostel;
import com.project.reservations_hotel.entity.HostelRating;
import com.project.reservations_hotel.exception.AlreadyExistsException;
import com.project.reservations_hotel.model.filter.HostelFilter;
import com.project.reservations_hotel.model.request.RatingRequest;
import com.project.reservations_hotel.repository.HostelRepository;
import com.project.reservations_hotel.repository.specification.HostelSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HostelService {

    private final HostelRepository hostelRepository;

    public List<Hostel> findBy(HostelFilter filter) {
        return hostelRepository.findAll(HostelSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    public List<Hostel> findAll() {
        return hostelRepository.findAll();
    }

    public Hostel findById(Long hostelId) {
        return hostelRepository.findById(hostelId)
                .orElseThrow(() -> new EntityNotFoundException("Hostel not found by id: " + hostelId));
    }

    public Hostel createHostel(Hostel hostel) {
        if(hostelRepository.existsByTitle(hostel.getTitle())) {
            throw new AlreadyExistsException("Hostel already exists by title: " + hostel.getTitle());
        }

        hostel.setRating(new HostelRating());

        return hostelRepository.save(hostel);
    }

    public Hostel updateHostel(Hostel hostel) {
        Hostel existedHostel = findById(hostel.getId());

        existedHostel.setId(hostel.getId());
        existedHostel.setTitle(hostel.getTitle());
        existedHostel.setAdTitle(hostel.getAdTitle());
        existedHostel.setDistanceFromCenter(hostel.getDistanceFromCenter());
        existedHostel.setAddress(hostel.getAddress());

        return hostelRepository.save(existedHostel);
    }

    public Hostel addRating(RatingRequest request) {
        Hostel hostel = findById(request.getHostelId());

        hostel.getRating().addRating(request.getRatingOfUser());

        return hostelRepository.save(hostel);
    }

    public void deleteById(Long hostelId) {
        hostelRepository.deleteById(hostelId);
    }

    public Long count() {
        return hostelRepository.count();
    }
}
