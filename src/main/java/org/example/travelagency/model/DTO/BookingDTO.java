package org.example.travelagency.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private int tourists;
    private Long userId;
    private Long tourId;
}
