package org.example.travelagency.controller;


import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.TourDTO;
import org.example.travelagency.model.Tour;
import org.example.travelagency.service.TourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class TourController {
    private final TourService tourService;

    @PostMapping("/admin/tours/add")
    public String addTour(@RequestBody TourDTO tour) {
        if (tourService.addTour(tour)) {
            return "Tour added successfully";
        }
        return "Tour not added";
    }

    @PostMapping("/admin/tours/delete")
    public String deleteTour(@RequestBody TourDTO tour) {
        if (tourService.deleteTour(tour)) {
            return "Tour deleted successfully";
        }
        return "Tour not deleted";
    }

    @GetMapping("/tours/all")
    public List<Tour> showAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/tours/all/{departureCity}")
    public List<Tour> showAllToursByDepartureCity(@PathVariable String departureCity) {
        return tourService.getAllToursByDepartureCity(departureCity);
    }

    @GetMapping("/tours/all/{departureCity}/price/{price}")
    public List<Tour> showAllToursByDepartureCityAndPrice(@PathVariable String departureCity, @PathVariable double price) {
        return tourService.getAllToursByDepartureCityAndPrice(departureCity, price);
    }

    @GetMapping("/tours/all/{departureCity}/hotel/{hotel}")
    public List<Tour> showAllToursByDepartureCityAndHotel(@PathVariable String departureCity, @PathVariable String hotel) {
        return tourService.getAllToursByDepartureCityAndHotel(departureCity, hotel);
    }

    @GetMapping("/tours/all/{departureCity}/tourists/{tourists}")
    public List<Tour> showAllToursByDepartureCityAndTourists(@PathVariable String departureCity, @PathVariable int tourists) {
        return tourService.getAllToursByDepartureCityAndMaxTourists(departureCity, tourists);
    }

    @GetMapping("/tours/all/{departureCity}/destinationCountry/{destinationCountry}")
    public List<Tour> showAllToursByDepartureCityAndDestinationCountry(@PathVariable String departureCity, @PathVariable String destinationCountry) {
        return tourService.getAllToursByDepartureCityAndDestinationCountry(departureCity, destinationCountry);
    }
    
    @GetMapping("/tours/all/{departureCity}/destinationCity/{destinationCity}")
    public List<Tour> showAllToursByDepartureCityAndDestinationCity(@PathVariable String departureCity, @PathVariable String destinationCity) {
        return tourService.getAllToursByDepartureCityAndDestinationCity(departureCity, destinationCity);
    }
}
