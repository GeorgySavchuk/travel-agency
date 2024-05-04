package org.example.travelagency.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travelagency.model.DTO.DepartureLocationDTO;
import org.example.travelagency.model.DepartureLocation;
import org.example.travelagency.repository.DepartureLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@Service
public class DepartureLocationService {
    private final DepartureLocationRepository departureLocationRepository;

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
            departureLocationRepository.delete(departureLocation.get());
            log.info("Departure location deleted {}", city);
            return true;
        }

        log.info("Departure location does not exist {}", city);
        return false;
    }

    public List<DepartureLocation> getAllDepartureLocations() {
        log.info("Get all departure locations");
        return departureLocationRepository.findAll();
    }
}
