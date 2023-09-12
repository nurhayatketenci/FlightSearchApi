package com.example.flightsearch.controller;

import com.example.flightsearch.dto.FlightInfoRequest;
import com.example.flightsearch.dto.OneWayFlightDto;
import com.example.flightsearch.dto.RoundTripFlightDto;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.service.FlightService;
import jakarta.annotation.Nullable;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        Flight flight = flightService.updateFlight(id, updatedFlight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/search/{departureAirport}/{arrivalAirport}/{departureDatetime}/{returnDatetime}")
    public ResponseEntity<List<?>> getFlightsByTerms(
            @PathVariable String departureAirport,
            @PathVariable String arrivalAirport,
            @PathVariable LocalDateTime departureDatetime,
            @PathVariable(required = false) LocalDateTime returnDatetime
    ) {
        if (returnDatetime != null) {
            List<RoundTripFlightDto> roundTripFlights = flightService.getRoundTripFlights(
                    departureAirport, arrivalAirport, departureDatetime, returnDatetime);
            return ResponseEntity.ok(roundTripFlights);
        } else {
            List<OneWayFlightDto> oneWayFlights = flightService.getOneWayFlights(
                    departureAirport, arrivalAirport, departureDatetime);
            return ResponseEntity.ok(oneWayFlights);
        }

    }


}
