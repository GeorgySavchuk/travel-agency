package org.example.travelagency.controller;


import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.DestinationLocationDTO;
import org.example.travelagency.model.DestinationLocation;
import org.example.travelagency.model.Tour;
import org.example.travelagency.service.DestinationLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/destinations")
public class DestinationLocationController {
    private final DestinationLocationService destinationLocationService;

    @GetMapping("/all")
    public List<DestinationLocation> showAllDestinationLocations() {
        return destinationLocationService.getAllDestinationLocations();
    }

    @GetMapping("/{country}")
    public List<DestinationLocation> showAllDestinationLocationByCountry(@PathVariable String country) {
        return destinationLocationService.getAllDestinationLocationsByCountry(country);
    }

    @GetMapping("/tours")
    public List<Tour> showAllToursByDestinationLocation(@RequestBody DestinationLocationDTO destinationLocation) {
        return destinationLocationService.getAllToursByDestinationLocation(destinationLocation);
    }
}
