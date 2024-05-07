package org.example.travelagency.controller;


import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.DepartureLocationDTO;
import org.example.travelagency.model.DTO.DestinationLocationDTO;
import org.example.travelagency.model.DTO.TourDTO;
import org.example.travelagency.service.DepartureLocationService;
import org.example.travelagency.service.DestinationLocationService;
import org.example.travelagency.service.TourService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final DepartureLocationService departureLocationService;
    private final DestinationLocationService destinationLocationService;
    private final TourService tourService;


    @PostMapping("/departures/add")
    public String addDepartureLocation(@RequestBody DepartureLocationDTO departureLocation) {
        if (departureLocationService.addDepartureLocation(departureLocation)) {
            return "Departure location added successfully";
        }
        return "Departure location not added";
    }

    @PostMapping("/departures/delete")
    public String deleteDepartureLocation(@RequestBody String city) {
        if (departureLocationService.deleteDepartureLocation(city)) {
            return "Departure location deleted successfully";
        }
        return "Departure location not deleted";
    }

    @PostMapping("/destinations/add")
    public String addDestinationLocation(@RequestBody DestinationLocationDTO destinationLocation) {
        if (destinationLocationService.addDestinationLocation(destinationLocation)) {
            return "Destination location added successfully";
        }
        return "Destination location not added";
    }

    @PostMapping("/destinations/delete")
    public String deleteDestinationLocation(@RequestBody String city) {
        if (destinationLocationService.deleteDestinationLocation(city)) {
            return "Destination location deleted successfully";
        }
        return "Destination location not deleted";
    }

    @PostMapping("/tours/add")
    public String addTour(@RequestBody TourDTO tour) {
        if (tourService.addTour(tour)) {
            return "Tour added successfully";
        }
        return "Tour not added";
    }

    @PostMapping("/tours/delete")
    public String deleteTour(@RequestBody TourDTO tour) {
        if (tourService.deleteTour(tour)) {
            return "Tour deleted successfully";
        }
        return "Tour not deleted";
    }
}
