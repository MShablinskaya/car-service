package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto dto);
}
