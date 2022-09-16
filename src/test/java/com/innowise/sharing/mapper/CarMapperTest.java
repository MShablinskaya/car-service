package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.util.CarTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarMapperTest {

    private final CarMapper carMapper = new CarMapperImpl();

    @Test
    void carDtoToCar_When_Success() {
        CarDto carDto = CarTestUtil.createCarDto();
        Car car = CarTestUtil.createCar();
        car.setOwner(null);

        assertThat(car)
                .usingRecursiveComparison()
                .isEqualTo(carMapper.carDtoToCar(carDto));
    }

    @Test
    void carToCarDto_When_Success() {
        CarDto carDto = CarTestUtil.createCarDto();
        carDto.setOwnerId(null);
        Car car = CarTestUtil.createCar();

        assertThat(carDto)
                .usingRecursiveComparison()
                .isEqualTo(carMapper.carToCarDto(car));
    }

}