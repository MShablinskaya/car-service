package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;

import java.util.List;

public interface CarService {
    CarDto findCarById(Long id);
    List<CarDto> getAllCars();
}
