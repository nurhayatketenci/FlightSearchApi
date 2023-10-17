package com.example.flightsearch.service;

import com.example.flightsearch.exceptions.AlreadyExistException;
import com.example.flightsearch.exceptions.NotFoundException;
import com.example.flightsearch.model.Airport;
import com.example.flightsearch.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public Airport addAirport(Airport airport) {
        boolean isExist=this.airportRepository.existsByCity(airport.getCity());
        if(isExist){
            throw new AlreadyExistException("This airport name already exists!");
        }
        return airportRepository.save(airport);
    }
    public Airport updateAirport(Long id, Airport updatedAirport) {
        Optional<Airport> existingAirportOptional = airportRepository.findById(id);
        if (existingAirportOptional.isPresent()) {
            Airport existingAirport = existingAirportOptional.get();
            //existingAirport.setCity(updatedAirport.getCity());
            return airportRepository.save(existingAirport);
        } else {
            throw new NotFoundException("Airport not found");
        }
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isPresent()) {
            return airportOptional.get();
        } else {
            throw new NotFoundException("Airport not found");
        }
    }
    public Airport findByCity(String city){
        return this.airportRepository.findByCity(city);
    }

}
