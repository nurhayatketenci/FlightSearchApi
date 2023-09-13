package com.example.flightsearch.service;

import com.example.flightsearch.dto.OneWayFlightDto;
import com.example.flightsearch.dto.RoundTripFlightDto;
import com.example.flightsearch.exceptions.NotFoundException;
import com.example.flightsearch.model.Airport;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final ModelMapper modelMapper;

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        Optional<Flight> existingFlightOptional = flightRepository.findById(id);
        if (existingFlightOptional.isPresent()) {
            Flight existingFlight = existingFlightOptional.get();
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureDatetime(updatedFlight.getDepartureDatetime());
            existingFlight.setReturnDatetime(updatedFlight.getReturnDatetime());
            existingFlight.setPrice(updatedFlight.getPrice());
            return flightRepository.save(existingFlight);
        } else {
            throw new NotFoundException("This Flight not found");
        }
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            return flightOptional.get();
        } else {
            throw new NotFoundException("This flight not found");
        }
    }

    public List<Flight> getByTerms(String departureAirport,
                                   String arrivalAirport,
                                   LocalDateTime departureDatetime,
                                   LocalDateTime returnDatetime) {
        Airport depAirport = this.airportService.findByCity(departureAirport);
        Airport arrAirport = this.airportService.findByCity(arrivalAirport);
        List<Flight> flights = this.flightRepository.findFlights(depAirport.getId(),
                arrAirport.getId(), departureDatetime, returnDatetime);
        return flights;
    }

    public List<RoundTripFlightDto> getRoundTripFlights(
            String departureAirport, String arrivalAirport, LocalDateTime departureDatetime, LocalDateTime returnDatetime) {
        Airport depAirport = this.airportService.findByCity(departureAirport);
        Airport arrAirport = this.airportService.findByCity(arrivalAirport);
        List<Flight> flights = this.flightRepository.findFlights(depAirport.getId(),
                arrAirport.getId(), departureDatetime, returnDatetime);
        List<RoundTripFlightDto> flightDtos = flights.stream()
                .map(flight -> this.modelMapper.map(flight, RoundTripFlightDto.class))
                .collect(Collectors.toList());

        return flightDtos;
    }

    public List<OneWayFlightDto> getOneWayFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDatetime) {
        Airport depAirport = this.airportService.findByCity(departureAirport);
        Airport arrAirport = this.airportService.findByCity(arrivalAirport);
        List<Flight> flights = this.flightRepository.findFlights(depAirport.getId(),
                arrAirport.getId(), departureDatetime, null);
        List<OneWayFlightDto> flightDtos = Collections.singletonList(this.modelMapper.map(flights, OneWayFlightDto.class));
        return flightDtos;
    }

}
