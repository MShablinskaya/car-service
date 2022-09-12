package com.innowise.sharing.repository;

import com.innowise.sharing.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.availability =: true")
    List<Car> selectAvailableCars();
}
