package org.example.travelagency.model.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date bookingDate;
    private double totalPrice;
    private int tourists;
    private Long userId;
    private Long tourId;
}
