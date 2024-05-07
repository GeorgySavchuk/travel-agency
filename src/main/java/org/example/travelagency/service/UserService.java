package org.example.travelagency.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travelagency.model.Booking;
import org.example.travelagency.model.DTO.BookingDTO;
import org.example.travelagency.model.Tour;
import org.example.travelagency.model.User;
import org.example.travelagency.repository.BookingRepository;
import org.example.travelagency.repository.TourRepository;
import org.example.travelagency.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;

    public boolean create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }else{
            userRepository.save(user);
            return true;
        }
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }


    public boolean addBooking(BookingDTO bookingDTO) {
        Optional<Tour> tour = tourRepository.findById(bookingDTO.getTourId());
        Optional<User> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (tour.isPresent() && user.isPresent()) {
            Booking booking = new Booking(
                    new Date(),
                    bookingDTO.getTourists() * tour.get().getPrice(),
                    bookingDTO.getTourists(),
                    user.get(),
                    tour.get()
            );
            if (bookingRepository.findByTour_IdAndUser_Id(tour.get().getId(), user.get().getId()).isEmpty()) {
                bookingRepository.save(booking);
                log.info("Booking added {}", bookingDTO);
                return true;
            }
        }
        log.info("Booking not added {}", bookingDTO);
        return false;
    }

    public boolean deleteBooking(BookingDTO bookingDTO) {
        Optional<Tour> tour = tourRepository.findById(bookingDTO.getTourId());
        Optional<User> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (tour.isPresent() && user.isPresent()) {
            Optional<Booking> booking = bookingRepository.findByTour_IdAndUser_Id(tour.get().getId(), user.get().getId());
            if (booking.isPresent()) {
                bookingRepository.delete(booking.get());
                log.info("Booking deleted {}", bookingDTO);
                return true;
            }
        }
        log.info("Booking not deleted {}", bookingDTO);
        return false;
    }


    public List<Booking> getUserBookings() {
        return bookingRepository.findAllByUser(userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()).get());
    }
}
