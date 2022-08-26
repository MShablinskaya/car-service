package com.innowise.sharing.controller.impl;

import com.innowise.sharing.controller.CarController;
import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CarControllerImpl implements CarController {
    private final CarService carService;

    @Override
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @Override
    public ResponseEntity<CarDto> getCarById(Long id) {
        return ResponseEntity.ok(carService.findCarById(id));
    }

    @Override
    public ResponseEntity<CarDto> changeCarAvailabilityStatus(Long id) {
        return ResponseEntity.ok(carService.changeAvailabilityStatus(id));
    }

    @Override
    public ResponseEntity<CarDto> addNewCarToList(CarDto dto) {
        return ResponseEntity.ok(carService.addNewCarToList(dto));
    }

    @Override
    public ResponseEntity deleteCar(Long id) {
        carService.deleteCar(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CarDto>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }
}
