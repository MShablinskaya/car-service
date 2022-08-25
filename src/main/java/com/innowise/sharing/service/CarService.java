package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.exception.CarException;

import java.util.List;

public interface CarService {
    CarDto findCarById(Long id);
    List<CarDto> getAllCars();
    List<CarDto> getAvailableCars();
    CarDto changeAvailabilityStatus(Long carI);
    CarDto addNewCarToList(CarDto dto) throws CarException;
    void deleteCar(Long carId) throws CarException;
}
