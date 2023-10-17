package com.example.flightsearch.repository;

import com.example.flightsearch.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Airport findByCity(String city);
    boolean existsByCity(String city);
}
