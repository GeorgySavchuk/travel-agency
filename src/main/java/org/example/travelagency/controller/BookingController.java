package org.example.travelagency.controller;


import lombok.AllArgsConstructor;
import org.example.travelagency.model.Booking;
import org.example.travelagency.model.DTO.BookingDTO;
import org.example.travelagency.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/add")
    public String addBooking(@RequestBody BookingDTO booking) {
        if (bookingService.addBooking(booking)) {
            return "Booking added";
        }
        return "Booking not added";
    }

    @PostMapping("/delete")
    public String deleteBooking(@RequestBody BookingDTO booking) {
        if (bookingService.deleteBooking(booking)) {
            return "Booking deleted";
        }
        return "Booking not deleted";
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{username}")
    public List<Booking> getBooking(@PathVariable String username) {
        return bookingService.getAllUserBookings(username);
    }
}
