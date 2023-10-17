package com.example.flightsearch.service;

import com.example.flightsearch.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

     @InjectMocks
     private FlightService flightService;

     @Mock
     private FlightRepository repository;

     @Mock
     private AirportService airportService;





}