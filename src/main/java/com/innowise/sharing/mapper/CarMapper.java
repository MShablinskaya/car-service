package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarDto carToCarDto(Car car);
    Car carDtoToCar(CarDto dto);
}
