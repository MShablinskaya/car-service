package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByAvailabilityTrue();
}
