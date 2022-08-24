package com.innowise.sharing.converters.impl;

import com.innowise.sharing.converters.CarDtoConverter;
import com.innowise.sharing.converters.UserDtoConverter;
import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarDtoConverterImpl implements CarDtoConverter {
    private final UserService userService;
    private final UserDtoConverter converter;
    @Override
    public CarDto toDto(Car car) {
        return new CarDto()
                .setBrand(car.getBrand())
                .setModel(car.getModel())
                .setColor(car.getColor())
                .setReleaseYear(car.getReleaseYear())
                .setRegistrationNumber(car.getRegistrationNumber())
                .setOwnerId(userService.getUserByEmail(car.getOwnerId().getEmail()))
                .setAvailability(car.getAvailability());
    }

    @Override
    public Car toEntity(CarDto dto) {
        return new Car()
                .setBrand(dto.getBrand())
                .setModel(dto.getModel())
                .setColor(dto.getColor())
                .setReleaseYear(dto.getReleaseYear())
                .setRegistrationNumber(dto.getRegistrationNumber())
                .setOwnerId(converter.toEntity(userService.getUserByEmail(dto.getOwnerId().getEmail())))
                .setAvailability(dto.getAvailability());
    }
}
