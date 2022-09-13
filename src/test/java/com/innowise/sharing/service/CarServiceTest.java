package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.mapper.CarMapper;
import com.innowise.sharing.repository.CarRepository;
import com.innowise.sharing.service.impl.CarServiceImpl;
import com.innowise.sharing.service.util.CarTestUtil;
import com.innowise.sharing.service.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper mapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void findCarDtoById_When_Successful() {
        Car expectedCar = CarTestUtil.createCar();
        CarDto expectedCarDto = CarTestUtil.createCarDto();
        UserDto ownerDto = UserTestUtil.createUserDto();

        when(userService.getUserDtoByEmail(UserTestUtil.EMAIL)).thenReturn(ownerDto);
        when(mapper.carToCarDto(expectedCar)).thenReturn(expectedCarDto);
        when(carRepository.findById(CarTestUtil.ID)).thenReturn(Optional.ofNullable(expectedCar));


        CarDto currentCar = carService.findCarDtoById(CarTestUtil.ID);

        assertThat(currentCar).isEqualTo(expectedCarDto);

    }

    @Test
    void getCarEntityById_When_Successful() {
        Car expectedCar = CarTestUtil.createCar();

        when(carRepository.findById(CarTestUtil.ID)).thenReturn(Optional.ofNullable(expectedCar));

        Car currentCar = carService.getCarEntityById(CarTestUtil.ID);

        assertThat(currentCar).isEqualTo(expectedCar);
    }

    @Test
    void getAllCars_When_Not_Empty() {
        Car expectedCar = CarTestUtil.createCar();
        CarDto expectedCarDto = CarTestUtil.createCarDto();
        UserDto ownerDto = UserTestUtil.createUserDto();
        List<Car> expectedCarList = new ArrayList<>();
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        expectedCarList.add(expectedCar);
        expectedCarDtoList.add(expectedCarDto);

        when(userService.getUserDtoByEmail(UserTestUtil.EMAIL)).thenReturn(ownerDto);
        when(mapper.carToCarDto(expectedCar)).thenReturn(expectedCarDto);
        when(carRepository.findAll()).thenReturn(expectedCarList);

        List<CarDto> currentCarList = carService.getAllCars();

        assertThat(currentCarList).isEqualTo(expectedCarDtoList);

    }

    @Test
    void getAvailableCars() {
        Car expectedCar = CarTestUtil.createCar();
        CarDto expectedCarDto = CarTestUtil.createCarDto();
        UserDto ownerDto = UserTestUtil.createUserDto();
        List<Car> expectedCarList = new ArrayList<>();
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        expectedCarList.add(expectedCar);
        expectedCarDtoList.add(expectedCarDto);

        when(userService.getUserDtoByEmail(UserTestUtil.EMAIL)).thenReturn(ownerDto);
        when(mapper.carToCarDto(expectedCar)).thenReturn(expectedCarDto);
        when(carRepository.selectAvailableCars()).thenReturn(expectedCarList);

        List<CarDto> currenAvailableCarList = carService.getAvailableCars();

        assertThat(currenAvailableCarList).isEqualTo(expectedCarDtoList);
    }

    @Test
    void changeAvailabilityStatus() {
        Car expectedCar = CarTestUtil.createCar();

        when(carRepository.findById(CarTestUtil.ID)).thenReturn(Optional.ofNullable(expectedCar));
        assert expectedCar != null;
        carService.changeAvailabilityStatus(CarTestUtil.ID);

        verify(carRepository).save(expectedCar);

    }

    @Test
    void addNewCarToList() {
        Car expectedCar = CarTestUtil.createCar();
        CarDto expectedCarDtoForSaving = CarTestUtil.createCarDto();
        User owner = UserTestUtil.createUser();

        when(userService.getUserByEmail(UserTestUtil.EMAIL)).thenReturn(owner);
        when(mapper.carDtoToCar(expectedCarDtoForSaving)).thenReturn(expectedCar);
        when(carRepository.save(expectedCar)).thenReturn(expectedCar);

        carService.addNewCarToList(expectedCarDtoForSaving);

        verify(carRepository).save(expectedCar);
    }

    @Test
    void deleteCar() {
        Car expectedCar = CarTestUtil.createCar();

        when(carRepository.findById(CarTestUtil.ID)).thenReturn(Optional.ofNullable(expectedCar));

        carService.deleteCar(CarTestUtil.ID);

        assert expectedCar != null;
        verify(carRepository).delete(expectedCar);
    }
}