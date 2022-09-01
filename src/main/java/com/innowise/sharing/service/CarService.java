package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface CarService {
    CarDto findCarDtoById(Long id);

    Car getCarEntityById(Long id);

    List<CarDto> getAllCars();

    List<CarDto> getAvailableCars();

    void changeAvailabilityStatus(Long carId);

    void addNewCarToList(@Valid CarDto dto);

    void deleteCar(Long carId);
}
