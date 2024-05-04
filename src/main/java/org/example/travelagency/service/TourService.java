package org.example.travelagency.service;

import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.TourDTO;
import org.example.travelagency.repository.DepartureLocationRepository;
import org.example.travelagency.repository.DestinationLocationRepository;
import org.example.travelagency.repository.TourRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TourService {
    private final TourRepository tourRepository;
    private final DestinationLocationRepository destinationLocationRepository;
    private final DepartureLocationRepository departureLocationRepository;


}
