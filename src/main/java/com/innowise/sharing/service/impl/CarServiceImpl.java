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

        return carRepository.findAll()
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getAvailableCars() {
        return carRepository.findCarsByAvailabilityTrue()
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto changeAvailabilityStatus(Long carId) {
        Car car = carRepository.getReferenceById(carId);
        changeStatus(car);
        return converter.toDto(car);
    }

    @Override
    public CarDto addNewCarToList(CarDto dto) {
        Car car = carRepository.save(converter.toEntity(dto));
        return converter.toDto(car);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.delete(carRepository.getReferenceById(carId));
    }

    private void changeStatus(Car car) {
        car.setAvailability(!car.getAvailability());
        carRepository.save(car);
    }
}
