package com.example.flightsearch.dto;

import com.example.flightsearch.model.Airport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoundTripFlightDto {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDatetime;
    private LocalDateTime returnDatetime;
    private int price;
}