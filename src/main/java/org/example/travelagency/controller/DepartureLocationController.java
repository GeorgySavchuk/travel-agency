package org.example.travelagency.controller;

import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.DepartureLocationDTO;
import org.example.travelagency.service.DepartureLocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DepartureLocationController {
    private final DepartureLocationService departureLocationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/departure/add")
    public String addDepartureLocation(@RequestBody DepartureLocationDTO departureLocation) {
        if (departureLocationService.addDepartureLocation(departureLocation)) {
            return "Departure location added successfully";
        }
        return "Departure location not added";
    }
}
