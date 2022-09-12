package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;

import java.util.List;

public interface CarService {
    CarDto findCarDtoById(Long id);

    Car getCarEntityById(Long id);

    List<CarDto> getAllCars();

    List<CarDto> getAvailableCars();

    void changeAvailabilityStatus(Long carId);

    void addNewCarToList(CarDto dto);

    void deleteCar(Long carId);
}
