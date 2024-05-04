package org.example.travelagency.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int nights;

    @Column(nullable = false)
    private int maxTourists;

    @Column(nullable = false)
    private String hotel;

    @JsonIgnoreProperties("tours")
    @ManyToOne
    private DestinationLocation destinationLocation;

    @JsonIgnoreProperties("tours")
    @ManyToOne
    private DepartureLocation departureLocation;

    @JsonIgnoreProperties("tour")
    @OneToMany(mappedBy = "tour")
    private List<Booking> bookings = new ArrayList<>();

    public Tour(Date startDate, Date endDate, double price, int nights, int maxTourists, String hotel, DestinationLocation destinationLocation, DepartureLocation departureLocation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.nights = nights;
        this.maxTourists = maxTourists;
        this.hotel = hotel;
        this.destinationLocation = destinationLocation;
        this.departureLocation = departureLocation;
    }
}
