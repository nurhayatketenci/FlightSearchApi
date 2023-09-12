package com.example.flightsearch.repository;

import java.util.Optional;

import com.example.flightsearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
