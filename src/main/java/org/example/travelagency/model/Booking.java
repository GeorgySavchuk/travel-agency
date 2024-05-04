package org.example.travelagency.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date bookingDate;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private int tourists;

    @JsonIgnoreProperties("bookings")
    @ManyToOne
    private User user;

    @JsonIgnoreProperties("bookings")
    @ManyToOne
    private Tour tour;

    public Booking(Date bookingDate, double totalPrice, int tourists, User user, Tour tour) {
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.tourists = tourists;
        this.user = user;
        this.tour = tour;
    }
}
