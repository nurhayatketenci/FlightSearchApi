package com.example.flightsearch.dto;

import com.example.flightsearch.model.Airport;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class FlightInfoRequest {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDatetime;
    @Nullable
    private LocalDateTime returnDatetime;
}
