package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
