package org.example.travelagency.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartureLocationDTO {
    private String country;
    private String city;
}
