package com.innowise.sharing.service.impl;

import com.innowise.sharing.converters.CarDtoConverter;
import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.repository.CarRepository;
import com.innowise.sharing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarDtoConverter converter;

    @Override
    public CarDto findCarById(Long id) {
        return converter.toDto(carRepository.getReferenceById(id));
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(converter::toDto).collect(Collectors.toList());
    }
}
