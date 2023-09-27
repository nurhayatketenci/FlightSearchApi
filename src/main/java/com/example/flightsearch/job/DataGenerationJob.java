package com.example.flightsearch.job;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.repository.FlightRepository;
import com.example.flightsearch.service.MockDataService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class DataGenerationJob {
    private final MockDataService mockDataService;
    private final FlightRepository flightRepository;

    @Scheduled(cron = "0 0 1 * * ?")

    public void generateMockData() {
        int numberOfFlightsToGenerate = 2;
        List<Flight> generatedFlights = mockDataService.generateMockFlights(numberOfFlightsToGenerate);
        this.flightRepository.saveAll(generatedFlights);
    }
}

