package com.example.flightsearch.controller;

import com.example.flightsearch.model.Airport;
import com.example.flightsearch.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
public class AirportController {
    private AirportService airportService;


    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.addAirport(airport);
        return new ResponseEntity<>(savedAirport, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport updatedAirport) {
        Airport airport = airportService.updateAirport(id, updatedAirport);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }
}
