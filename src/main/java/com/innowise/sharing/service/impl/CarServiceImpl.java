package com.innowise.sharing.service.impl;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.kafka.producer.MessageProducer;
import com.innowise.sharing.mapper.CarMapper;
import com.innowise.sharing.repository.CarRepository;
import com.innowise.sharing.service.CarService;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final UserService userService;
    private final CarMapper mapper;
    @Autowired
    @Qualifier("carMessageProducer")
    private MessageProducer<Car> messageProducer;

    @Override
    public CarDto findCarDtoById(Long id) {
        return carRepository.findById(id)
                .map(this::setOwnerDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Car getCarEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::setOwnerDto)
                .toList();
    }

    @Override
    public List<CarDto> getAvailableCars() {
        return carRepository.selectAvailableCars()
                .stream()
                .map(this::setOwnerDto)
                .toList();
    }

    @Override
    public void changeAvailabilityStatus(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        car.setAvailability(!car.getAvailability());
        carRepository.save(car);
    }

    @Override
    public void addNewCarToList(CarDto dto) {
        Car car = mapper.carDtoToCar(dto);
        String email = dto.getOwnerId().getEmail();
        User user = userService.getUserByEmail(email);
        car.setOwner(user);
        carRepository.save(car);

        messageProducer.send(car);
    }

    @Override
    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        carRepository.delete(car);
    }

    private CarDto setOwnerDto(Car car) {
        String email = car.getOwner().getEmail();
        UserDto owner = userService.getUserDtoByEmail(email);
        CarDto carDto = mapper.carToCarDto(car);
        carDto.setOwnerId(owner);

        return carDto;
    }
}
