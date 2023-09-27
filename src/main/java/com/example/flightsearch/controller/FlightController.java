package com.example.flightsearch.controller;

import com.example.flightsearch.dto.FlightInfo;
import com.example.flightsearch.dto.OneWayFlightDto;
import com.example.flightsearch.dto.RoundTripFlightDto;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@Tag(name = "Flight Search API v1", description = "Applies flight filter in flight search project")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(
            method = "GET",
            summary = "Get all flight",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get all flight",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = RoundTripFlightDto.class)))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "There is no available flight",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )

    @GetMapping("/getall")
    public ResponseEntity<List<RoundTripFlightDto>> getAllFlights() {
        List<RoundTripFlightDto> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(
            method = "GET",
            summary = "get all flights by terms",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "get all flights by terms",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = FlightInfo.class)))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "There is no available flight by terms",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )

    @GetMapping
    public ResponseEntity<List<FlightInfo>> getFlightsByTerms(@RequestBody FlightInfo flightInfoRequest) {
        List<FlightInfo> flightInfos = flightService.getFlightByTerms(flightInfoRequest);
        return ResponseEntity.ok(flightInfos);
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

    @GetMapping("/{id}")
    public ResponseEntity<RoundTripFlightDto> getFlightById(@PathVariable Long id) {
        RoundTripFlightDto flight = flightService.getFlightById(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }


}
