package com.innowise.sharing.converters.impl;

import com.innowise.sharing.converters.CarDtoConverter;
import com.innowise.sharing.converters.UserDtoConverter;
import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarDtoConverterImpl implements CarDtoConverter {
    private final UserDtoConverter converter;

    @Override
    public CarDto toDto(Car car) {
        return CarDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .color(car.getColor())
                .releaseYear(car.getReleaseYear())
                .registrationNumber(car.getRegistrationNumber())
                .ownerId(converter.toDto(car.getOwner()))
                .availability(car.getAvailability())
                .build();
    }

    @Override
    public Car toEntity(CarDto dto) {
        return null;
    }
}
