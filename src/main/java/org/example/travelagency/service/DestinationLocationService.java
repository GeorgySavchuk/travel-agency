package org.example.travelagency.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travelagency.model.DTO.DestinationLocationDTO;
import org.example.travelagency.model.DestinationLocation;
import org.example.travelagency.model.Tour;
import org.example.travelagency.repository.DestinationLocationRepository;
import org.example.travelagency.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class DestinationLocationService {
    private final DestinationLocationRepository destinationLocationRepository;
    private final TourRepository tourRepository;

    public boolean addDestinationLocation(DestinationLocationDTO destinationLocationDTO) {
        DestinationLocation destinationLocation = new DestinationLocation(destinationLocationDTO.getCountry(), destinationLocationDTO.getCity());
        if (destinationLocationRepository.findByCity(destinationLocation.getCity()).isEmpty()) {
            destinationLocationRepository.save(destinationLocation);
            log.info("Destination location added {}", destinationLocationDTO);
            return true;
        }
        log.info("Destination location already exists {}", destinationLocationDTO);
        return false;
    }

    public boolean deleteDestinationLocation(String city) {
        Optional<DestinationLocation> destinationLocation = destinationLocationRepository.findByCity(city);

        if (destinationLocation.isPresent()) {
            List<Tour> tours = tourRepository.findAllByDestinationLocation(destinationLocation.get());
            tourRepository.deleteAll(tours);
            destinationLocationRepository.delete(destinationLocation.get());
            log.info("Destination location deleted {}", city);
            return true;
        }

        log.info("Destination location does not exist {}", city);
        return false;
    }

    public List<DestinationLocation> getAllDestinationLocations() {
        log.info("Getting all destination locations");
        return destinationLocationRepository.findAll();
    }

    public List<DestinationLocation> getAllDestinationLocationsByCountry(String country) {
        log.info("Getting all destination locations by country {}", country);
        return destinationLocationRepository.findAllByCountry(country);
    }

    public List<Tour> getAllToursByDestinationLocation(DestinationLocationDTO destinationLocationDTO) {
        Optional<DestinationLocation> destinationLocation = destinationLocationRepository.findByCity(destinationLocationDTO.getCity());
        if (destinationLocation.isPresent()) {
            log.info("Getting all tours by destination location {}", destinationLocationDTO);
            return tourRepository.findAllByDestinationLocation(destinationLocation.get());
        }
        log.info("There is no such destination location {}", destinationLocationDTO);
        return Collections.emptyList();
    }
}
