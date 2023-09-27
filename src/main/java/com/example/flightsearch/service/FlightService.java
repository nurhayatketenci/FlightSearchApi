package com.example.flightsearch.service;

import com.example.flightsearch.dto.FlightInfo;
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

    public List<RoundTripFlightDto> getAllFlights() {
        List<Flight> flights=this.flightRepository.findAll();
        List<RoundTripFlightDto> flightDtos = Collections.singletonList(this.modelMapper.map(flights, RoundTripFlightDto.class));
        return flightDtos;

    }

    public RoundTripFlightDto getFlightById(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            RoundTripFlightDto flightDto =  this.modelMapper.map(flightOptional, RoundTripFlightDto.class);
            return flightDto;
        } else {
            throw new NotFoundException("This flight not found");
        }
    }

    public List<FlightInfo> getFlightByTerms(FlightInfo flightInfo){
        Airport depAirport = this.airportService.findByCity(flightInfo.getDepartureAirport().getCity());
        Airport arrAirport = this.airportService.findByCity(flightInfo.getArrivalAirport().getCity());

        List<Flight> flights = this.flightRepository.findFlights(depAirport.getId(),
                arrAirport.getId(),flightInfo.getDepartureDatetime(), flightInfo.getReturnDatetime());
        List<FlightInfo> flightInfos = Collections.singletonList(this.modelMapper.map(flights, FlightInfo.class));
        return flightInfos;
    }

}
