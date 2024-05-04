package org.example.travelagency.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "departureLocations")
public class DepartureLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @JsonIgnoreProperties("departureLocation")
    @OneToMany(mappedBy = "departureLocation")
    private List<Tour> tours = new ArrayList<>();

    public DepartureLocation(String country, String city) {
        this.country = country;
        this.city = city;
    }
}
