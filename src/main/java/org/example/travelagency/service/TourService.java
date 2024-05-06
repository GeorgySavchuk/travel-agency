package org.example.travelagency.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travelagency.model.Booking;
import org.example.travelagency.model.DTO.TourDTO;
import org.example.travelagency.model.DepartureLocation;
import org.example.travelagency.model.DestinationLocation;
import org.example.travelagency.model.Tour;
import org.example.travelagency.repository.BookingRepository;
import org.example.travelagency.repository.DepartureLocationRepository;
import org.example.travelagency.repository.DestinationLocationRepository;
import org.example.travelagency.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class TourService {
    private final TourRepository tourRepository;
    private final DestinationLocationRepository destinationLocationRepository;
    private final DepartureLocationRepository departureLocationRepository;
    private final BookingRepository bookingRepository;

    public boolean addTour(TourDTO tourDTO) {
        Optional<DestinationLocation> destinationLocation = destinationLocationRepository.findById(tourDTO.getDestinationLocationId());
        Optional<DepartureLocation> departureLocation = departureLocationRepository.findById(tourDTO.getDepartureLocationId());
        if (destinationLocation.isPresent() && departureLocation.isPresent()) {
            if (tourRepository.findByHotelAndStartDateAndEndDateAndDepartureLocation_City(
                    tourDTO.getHotel(),
                    tourDTO.getStartDate(),
                    tourDTO.getEndDate(),
                    departureLocation.get().getCity()).isEmpty()
            ) {
                tourRepository.save(new Tour(
                        tourDTO.getStartDate(),
                        tourDTO.getEndDate(),
                        tourDTO.getPrice(),
                        tourDTO.getMaxTourists(),
                        tourDTO.getHotel(),
                        destinationLocation.get(),
                        departureLocation.get()
                ));
                log.info("Tour added {}", tourDTO);
                return true;
            }
            log.info("Tour already exists {}", tourDTO);
            return false;
        }
        log.info("Tour not added {}", tourDTO);
        return false;
    }

    public boolean deleteTour(TourDTO tourDTO) {
        Optional<DepartureLocation> departureLocation = departureLocationRepository.findById(tourDTO.getDepartureLocationId());
        if (departureLocation.isPresent()) {
            Optional<Tour> tour = tourRepository.findByHotelAndStartDateAndEndDateAndDepartureLocation_City(
                    tourDTO.getHotel(),
                    tourDTO.getStartDate(),
                    tourDTO.getEndDate(),
                    departureLocation.get().getCity());
            if (tour.isPresent()) {
                List<Booking> tourBookings = bookingRepository.findAllByTour(tour.get());
                bookingRepository.deleteAll(tourBookings);
                tourRepository.delete(tour.get());
                log.info("Tour deleted {}", tourDTO);
                return true;
            }
        }
        log.info("Tour not deleted");
        return false;
    }

    public List<Tour> getAllTours() {
        log.info("Getting all tours");
        return tourRepository.findAll();
    }

    public List<Tour> getAllToursByDepartureCity(String departureCity) {
        log.info("Getting all tours by departure city {}", departureCity);
        return tourRepository.findAllByDepartureLocation_City(departureCity);
    }

    public List<Tour> getAllToursByDepartureCityAndHotel(String departureCity, String hotel) {
        log.info("Getting all tours by hotel {}", hotel);
        return tourRepository.findAllByHotelAndDepartureLocation_City(hotel, departureCity);
    }

    public List<Tour> getAllToursByDepartureCityAndMaxTourists(String departureCity, int maxTourists) {
        log.info("Getting all tours by max tourists {}", maxTourists);
        return tourRepository.findByMaxTouristsLessThanEqualAndDepartureLocation_City(maxTourists, departureCity);
    }

    public List<Tour> getAllToursByDepartureCityAndPrice(String departureCity, double price) {
        log.info("Getting all tours by price {}", price);
        return tourRepository.findByPriceLessThanEqualAndDepartureLocation_City(price, departureCity);
    }

    public List<Tour> getAllToursByDepartureCityAndDestinationCountry(String departureCity, String destinationCountry) {
        log.info("Getting all tours by destination country {}", destinationCountry);
        return tourRepository.findAllByDestinationLocation_CountryAndDepartureLocation_City(destinationCountry, departureCity);
    }

    public List<Tour> getAllToursByDepartureCityAndDestinationCity(String departureCity, String destinationCity) {
        log.info("Getting all tours by destination city {}", destinationCity);
        return tourRepository.findAllByDestinationLocation_CityAndDepartureLocation_City(destinationCity, departureCity);
    }
}
