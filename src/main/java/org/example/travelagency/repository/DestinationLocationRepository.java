package org.example.travelagency.repository;

import org.example.travelagency.model.DestinationLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationLocationRepository extends JpaRepository<DestinationLocation, Long> {
}
