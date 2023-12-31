package com.example.flightsearch.dto;

import com.example.flightsearch.model.Airport;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class FlightInfo {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDatetime;
    @Nullable
    private LocalDateTime returnDatetime;
}
