package com.example.flightsearch.service;

import com.example.flightsearch.model.Airport;
import com.example.flightsearch.model.Flight;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class MockDataService {

    private final AirportService airportService;

    public List<Flight> generateMockFlights(int numberOfFlights) {
        List<Flight> mockFlights = new ArrayList<>();
        for (int i = 0; i < numberOfFlights; i++) {
            Flight flight = generateRandomFlight();
            mockFlights.add(flight);
        }
        return mockFlights;
    }

    private Flight generateRandomFlight() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime minDepartureDatetime = currentDateTime.plusDays(7);
        LocalDateTime maxDepartureDatetime = currentDateTime.plusMonths(6);
        LocalDateTime randomDepartureDatetime = getRandomDateTime(minDepartureDatetime, maxDepartureDatetime);

        LocalDateTime minReturnDatetime = randomDepartureDatetime.plusDays(1);
        LocalDateTime maxReturnDatetime = randomDepartureDatetime.plusMonths(6);
        LocalDateTime randomReturnDatetime = getRandomDateTime(minReturnDatetime, maxReturnDatetime);
        int randomPrice = getRandomPrice(100, 1000);

        Airport departureAirport = getRandomAirport();
        Airport arrivalAirport = getRandomAirport();
        this.airportService.addAirport(departureAirport);
        this.airportService.addAirport(arrivalAirport);

        return Flight.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDatetime(randomDepartureDatetime)
                .returnDatetime(randomReturnDatetime)
                .price(randomPrice)
                .build();
    }

    private LocalDateTime getRandomDateTime(LocalDateTime min, LocalDateTime max) {
        Random random = new Random();
        long minEpochSecond = min.toEpochSecond(ZoneOffset.UTC);
        long maxEpochSecond = max.toEpochSecond(ZoneOffset.UTC);
        long randomEpochSecond = ThreadLocalRandom.current().nextLong(minEpochSecond, maxEpochSecond);
        return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, ZoneOffset.UTC);
    }


    private int getRandomPrice(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private Airport getRandomAirport() {

        List<String> cities = Arrays.asList("Istanbul", "Ankara", "Izmir", "Antalya", "London", "Paris", "Berlin", "New York");

        Random random = new Random();
        int randomIndex = random.nextInt(cities.size());
        String randomCity = cities.get(randomIndex);

        return Airport.builder()
                .city(randomCity)
                .build();
    }
}
