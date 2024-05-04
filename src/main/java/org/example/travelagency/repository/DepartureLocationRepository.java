package org.example.travelagency.repository;

import org.example.travelagency.model.DepartureLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartureLocationRepository extends JpaRepository<DepartureLocation, Long> {
    Optional<DepartureLocation> findByCity(String city);
}
