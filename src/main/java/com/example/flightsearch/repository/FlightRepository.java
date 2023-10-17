package com.example.flightsearch.repository;

import com.example.flightsearch.model.Flight;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDatetimeGreaterThanEqual(Long departureAirportId,
                                                                                             Long arrivalAirportId,
                                                                                             LocalDateTime departureDateTime);
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDatetimeGreaterThanEqualAndReturnDatetimeGreaterThanEqual(
            Long departureAirportId,
            Long arrivalAirportId,
            LocalDateTime departureDateTime,
            LocalDateTime returnDateTime
    );
}
