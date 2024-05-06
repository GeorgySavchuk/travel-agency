package org.example.travelagency.repository;

import org.example.travelagency.model.DestinationLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DestinationLocationRepository extends JpaRepository<DestinationLocation, Long> {
    Optional<DestinationLocation> findByCity(String city);
    Optional<DestinationLocation> findByCountry(String country);
    List<DestinationLocation> findAllByCountry(String country);
}
