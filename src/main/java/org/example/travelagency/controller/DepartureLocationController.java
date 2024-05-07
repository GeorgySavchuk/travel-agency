package org.example.travelagency.controller;

import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.DepartureLocationDTO;
import org.example.travelagency.model.DepartureLocation;
import org.example.travelagency.model.Tour;
import org.example.travelagency.service.DepartureLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/departures")
public class DepartureLocationController {
    private final DepartureLocationService departureLocationService;

    @GetMapping("/all")
    public List<DepartureLocation> showAllDepartureLocations() {
        return departureLocationService.getAllDepartureLocations();
    }

    @GetMapping("/{country}")
    public List<DepartureLocation> showAllDepartureLocationByCountry(@PathVariable String country) {
        return departureLocationService.getAllDepartureLocationsByCountry(country);
    }

    @GetMapping("/tours")
    public List<Tour> showAllToursByDepartureLocation(@RequestBody DepartureLocationDTO departureLocation) {
        return departureLocationService.getAllToursByDepartureLocation(departureLocation);
    }
}
