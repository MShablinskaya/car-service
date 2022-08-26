package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;

import java.util.List;

public interface CarService {
    CarDto findCarById(Long id);

    List<CarDto> getAllCars();

    List<CarDto> getAvailableCars();

    CarDto changeAvailabilityStatus(Long carId);

    CarDto addNewCarToList(CarDto dto);

    void deleteCar(Long carId);
}
