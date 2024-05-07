package org.example.travelagency.repository;

import org.example.travelagency.model.Booking;
import org.example.travelagency.model.Tour;
import org.example.travelagency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByTour_IdAndUser_Id(Long tourId, Long userId);
    List<Booking> findALlByUser_Username(String username);
    List<Booking> findAllByTour(Tour tour);
    List<Booking> findAllByUser(User user);
}
