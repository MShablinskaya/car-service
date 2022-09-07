package com.innowise.sharing.controller.impl;

import com.innowise.sharing.controller.CarRest;
import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarRestImpl implements CarRest {
    private final CarService carService;

    @Override
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @Override
    public ResponseEntity<CarDto> getCarById(Long id) {
        return ResponseEntity.ok(carService.findCarDtoById(id));
    }

    @Override
    public void changeCarAvailabilityStatus(Long id) {
        carService.changeAvailabilityStatus(id);
    }

    @Override
    public void addNewCarToList(CarDto dto) {
        carService.addNewCarToList(dto);
    }

    @Override
    public void deleteCar(Long id) {
        carService.deleteCar(id);
    }

    @Override
    public ResponseEntity<List<CarDto>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }
}
