package org.example.travelagency.controller;


import lombok.AllArgsConstructor;
import org.example.travelagency.model.Tour;
import org.example.travelagency.service.TourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    @GetMapping("/all")
    public List<Tour> showAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/all/{departureCity}")
    public List<Tour> showAllToursByDepartureCity(@PathVariable String departureCity) {
        return tourService.getAllToursByDepartureCity(departureCity);
    }

    @GetMapping("/all/{departureCity}/price/{price}")
    public List<Tour> showAllToursByDepartureCityAndPrice(@PathVariable String departureCity, @PathVariable double price) {
        return tourService.getAllToursByDepartureCityAndPrice(departureCity, price);
    }

    @GetMapping("/all/{departureCity}/hotel/{hotel}")
    public List<Tour> showAllToursByDepartureCityAndHotel(@PathVariable String departureCity, @PathVariable String hotel) {
        return tourService.getAllToursByDepartureCityAndHotel(departureCity, hotel);
    }

    @GetMapping("/all/{departureCity}/tourists/{tourists}")
    public List<Tour> showAllToursByDepartureCityAndTourists(@PathVariable String departureCity, @PathVariable int tourists) {
        return tourService.getAllToursByDepartureCityAndMaxTourists(departureCity, tourists);
    }

    @GetMapping("/all/{departureCity}/destinationCountry/{destinationCountry}")
    public List<Tour> showAllToursByDepartureCityAndDestinationCountry(@PathVariable String departureCity, @PathVariable String destinationCountry) {
        return tourService.getAllToursByDepartureCityAndDestinationCountry(departureCity, destinationCountry);
    }
    
    @GetMapping("/all/{departureCity}/destinationCity/{destinationCity}")
    public List<Tour> showAllToursByDepartureCityAndDestinationCity(@PathVariable String departureCity, @PathVariable String destinationCity) {
        return tourService.getAllToursByDepartureCityAndDestinationCity(departureCity, destinationCity);
    }
}
