package org.example.travelagency.controller;

import lombok.AllArgsConstructor;
import org.example.travelagency.model.Booking;
import org.example.travelagency.model.DTO.BookingDTO;
import org.example.travelagency.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/bookings/add")
    public String addBooking(@RequestBody BookingDTO booking) {
        if (userService.addBooking(booking)) {
            return "Booking added";
        }
        return "Booking not added";
    }

    @PostMapping("/bookings/delete")
    public String deleteBooking(@RequestBody BookingDTO booking) {
        if (userService.deleteBooking(booking)) {
            return "Booking deleted";
        }
        return "Booking not deleted";
    }

    @GetMapping("/bookings/all")
    public List<Booking> getAllBookings() {
        return userService.getUserBookings();
    }
}
