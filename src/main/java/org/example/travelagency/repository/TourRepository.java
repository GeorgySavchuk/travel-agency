package org.example.travelagency.repository;

import org.example.travelagency.model.DepartureLocation;
import org.example.travelagency.model.DestinationLocation;
import org.example.travelagency.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {
    Optional<Tour> findByHotelAndStartDateAndEndDateAndDepartureLocation_City(String hotel, Date startDate, Date endDate, String departureCity);
    List<Tour> findAllByDepartureLocation_City(String departureCity);
    List<Tour> findAllByHotelAndDepartureLocation_City(String hotel, String departureCity);
    List<Tour> findByMaxTouristsLessThanEqualAndDepartureLocation_City(int maxTourists, String departureCity);
    List<Tour> findByPriceLessThanEqualAndDepartureLocation_City(double price, String departureCity);
    List<Tour> findAllByDestinationLocation_CountryAndDepartureLocation_City(String destinationCountry, String departureCity);
    List<Tour> findAllByDestinationLocation_CityAndDepartureLocation_City(String destinationCity, String departureCity);
    List<Tour> findAllByDepartureLocation(DepartureLocation departureLocation);
    List<Tour> findAllByDestinationLocation(DestinationLocation destinationLocation);
    List<Tour> findAllByStartDateGreaterThanEqualAndDepartureLocation_City(Date startDate, String departureCity);
}
