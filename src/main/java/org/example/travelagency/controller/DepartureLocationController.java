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
public class DepartureLocationController {
    private final DepartureLocationService departureLocationService;

    @PostMapping("/admin/departures/add")
    public String addDepartureLocation(@RequestBody DepartureLocationDTO departureLocation) {
        if (departureLocationService.addDepartureLocation(departureLocation)) {
            return "Departure location added successfully";
        }
        return "Departure location not added";
    }

    @PostMapping("/admin/departures/delete")
    public String deleteDepartureLocation(@RequestBody String city) {
        if (departureLocationService.deleteDepartureLocation(city)) {
            return "Departure location deleted successfully";
        }
        return "Departure location not deleted";
    }

    @GetMapping("/departures/all")
    public List<DepartureLocation> showAllDepartureLocations() {
        return departureLocationService.getAllDepartureLocations();
    }

    @GetMapping("/departures/{country}")
    public List<DepartureLocation> showAllDepartureLocationByCountry(@PathVariable String country) {
        return departureLocationService.getAllDepartureLocationsByCountry(country);
    }

    @GetMapping("/departures/tours")
    public List<Tour> showAllToursByDepartureLocation(@RequestBody DepartureLocationDTO departureLocation) {
        return departureLocationService.getAllToursByDepartureLocation(departureLocation);
    }
}
