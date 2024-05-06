package org.example.travelagency.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travelagency.model.DTO.DepartureLocationDTO;
import org.example.travelagency.model.DepartureLocation;
import org.example.travelagency.model.Tour;
import org.example.travelagency.repository.DepartureLocationRepository;
import org.example.travelagency.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@Service
public class DepartureLocationService {
    private final DepartureLocationRepository departureLocationRepository;
    private final TourRepository tourRepository;

    public boolean addDepartureLocation(DepartureLocationDTO departureLocationDTO) {
        DepartureLocation departureLocation = new DepartureLocation(departureLocationDTO.getCountry(), departureLocationDTO.getCity());
        if (departureLocationRepository.findByCity(departureLocation.getCity()).isEmpty()) {
            departureLocationRepository.save(departureLocation);
            log.info("Departure location added {}", departureLocationDTO);
            return true;
        }
        log.info("Departure location already exists {}", departureLocationDTO);
        return false;
    }

    public boolean deleteDepartureLocation(String city) {
        Optional<DepartureLocation> departureLocation = departureLocationRepository.findByCity(city);

        if (departureLocation.isPresent()) {
            List<Tour> tours = tourRepository.findAllByDepartureLocation(departureLocation.get());
            tourRepository.deleteAll(tours);
            departureLocationRepository.delete(departureLocation.get());
            log.info("Departure location deleted {}", city);
            return true;
        }

        log.info("Departure location does not exist {}", city);
        return false;
    }

    public List<DepartureLocation> getAllDepartureLocations() {
        log.info("Getting all departure locations");
        return departureLocationRepository.findAll();
    }

    public List<DepartureLocation> getAllDepartureLocationsByCountry(String country) {
        log.info("Getting all departure locations by country {}", country);
        return departureLocationRepository.findAllByCountry(country);
    }

    public List<Tour> getAllToursByDepartureLocation(DepartureLocationDTO departureLocationDTO) {
        Optional<DepartureLocation> departureLocation = departureLocationRepository.findByCity(departureLocationDTO.getCity());
        if (departureLocation.isPresent()) {
            log.info("Getting all tours by departure location {}", departureLocationDTO);
            return tourRepository.findAllByDepartureLocation(departureLocation.get());
        }
        log.info("There is no such departure location {}", departureLocationDTO);
        return Collections.emptyList();
    }
}
