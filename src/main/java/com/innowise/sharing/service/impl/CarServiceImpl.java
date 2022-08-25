package com.innowise.sharing.service.impl;

import com.innowise.sharing.converters.CarDtoConverter;
import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.exception.CarException;
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
        if (carId != null){
            Car car = carRepository.getReferenceById(carId);
            changeStatus(car);
            return converter.toDto(car);
        }else{
            throw new NullPointerException("Car Id can't being null");
        }
    }

    @Override
    public CarDto addNewCarToList(CarDto dto) throws CarException {
        if(dto != null){
            Car car = carRepository.save(converter.toEntity(dto));
            return converter.toDto(car);
        }else{
            throw new CarException("Ooops! Something went wrong. Check your input.");
        }
    }

    @Override
    public void deleteCar(Long carId) throws CarException {
       if(carId != null){
           carRepository.delete(carRepository.getReferenceById(carId));
       }else{
           throw new CarException("Car Id cant be null");
       }
    }

    private void changeStatus(Car car){
        car.setAvailability(!car.getAvailability());
        carRepository.save(car);
    }
}
