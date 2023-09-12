package com.example.flightsearch.dto;

import com.example.flightsearch.model.Airport;

import java.time.LocalDateTime;

public class OneWayFlightDto {
    private Long id;
    private Airport departureCity;
    private Airport arrivalCity;
    private LocalDateTime departureDatetime;
    private int price;
}
