package com.innowise.sharing.converters;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;

public interface CarDtoConverter {
    CarDto toDto(Car car);

    Car toEntity(CarDto dto);
}
