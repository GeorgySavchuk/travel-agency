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
public class DestinationLocationController {
    private final DestinationLocationService destinationLocationService;

    @PostMapping("/admin/destinations/add")
    public String addDestinationLocation(@RequestBody DestinationLocationDTO destinationLocation) {
        if (destinationLocationService.addDestinationLocation(destinationLocation)) {
            return "Destination location added successfully";
        }
        return "Destination location not added";
    }

    @PostMapping("/admin/destinations/delete")
    public String deleteDestinationLocation(@RequestBody String city) {
        if (destinationLocationService.deleteDestinationLocation(city)) {
            return "Destination location deleted successfully";
        }
        return "Destination location not deleted";
    }

    @GetMapping("/destinations/all")
    public List<DestinationLocation> showAllDestinationLocations() {
        return destinationLocationService.getAllDestinationLocations();
    }

    @GetMapping("/destinations/{country}")
    public List<DestinationLocation> showAllDestinationLocationByCountry(@PathVariable String country) {
        return destinationLocationService.getAllDestinationLocationsByCountry(country);
    }

    @GetMapping("/destinations/tours")
    public List<Tour> showAllToursByDestinationLocation(@RequestBody DestinationLocationDTO destinationLocation) {
        return destinationLocationService.getAllToursByDestinationLocation(destinationLocation);
    }
}
