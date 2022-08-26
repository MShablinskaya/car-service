package com.innowise.sharing.service.impl;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.mapper.CarMapper;
import com.innowise.sharing.repository.CarRepository;
import com.innowise.sharing.service.CarService;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final UserService userService;
    private final CarMapper mapper;

    @Override
    public CarDto findCarById(Long id) {
        return carRepository.findById(id)
                .map(this::setOwnerDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::setOwnerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getAvailableCars() {
        return carRepository.selectAvailableCars()
                .stream()
                .map(this::setOwnerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto changeAvailabilityStatus(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        car = changeStatus(car);
        return setOwnerDto(car);
    }

    @Override
    public CarDto addNewCarToList(CarDto dto) {
        Car car = carRepository.save(mapper.carDtoToCar(dto));
        return setOwnerDto(car);
    }

    @Override
    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        carRepository.delete(car);
    }

    private Car changeStatus(Car car) {
        car.setAvailability(!car.getAvailability());
        return carRepository.save(car);
    }

    private CarDto setOwnerDto(Car car) {
        String email = car.getOwner().getEmail();
        UserDto owner = userService.getUserByEmail(email);
        CarDto carDto = mapper.carToCarDto(car);
        carDto.setOwnerId(owner);

        return carDto;
    }
}
