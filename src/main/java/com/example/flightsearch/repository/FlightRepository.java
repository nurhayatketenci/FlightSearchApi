package com.example.flightsearch.repository;

import com.example.flightsearch.model.Flight;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("SELECT f FROM Flight f " +
            "WHERE f.departureAirport.id = :departureAirportId " +
            "AND f.arrivalAirport.id = :arrivalAirportId " +
            "AND f.departureDatetime >= :departureDatetime " +
            "AND (f.returnDatetime IS NULL OR f.returnDatetime <= :returnDatetime)")
    List<Flight> findFlights(
            @Param("departureAirportId") Long departureAirportId,
            @Param("arrivalAirportId") Long arrivalAirportId,
            @Param("departureDatetime") LocalDateTime departureDatetime,
            @Param("returnDatetime")  @Nullable LocalDateTime returnDatetime
    );
}
