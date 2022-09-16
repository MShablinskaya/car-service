package com.innowise.sharing.it.tests.repository;

import com.innowise.sharing.entity.Car;
import com.innowise.sharing.it.BaseIntegrationTest;
import com.innowise.sharing.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Sql({"/data/erasure_documents.sql", "/data/insert_documents.sql",
        "/data/erasure_users.sql", "/data/insert_users.sql",
        "/data/erasure_cars.sql", "/data/insert_cars.sql"})
class CarRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CarRepository carRepository;

    private final Car expectedCar = new Car()
            .setId(1L)
            .setBrand("Mercedes")
            .setModel("AMG GT")
            .setColor("silver")
            .setReleaseYear(2015)
            .setRegistrationNumber("6446 II-1")
            .setAvailability(true);

    @Test
    void findAll_When_Success() {
        List<Car> currentList = carRepository.findAll();

        assertNotNull(currentList);
        assertEquals(1, currentList.size());
    }

    @Test
    void findById_When_Success() {
        Car currentCar = carRepository.findById(1L).orElseThrow();

        assertNotNull(currentCar);
        assertThat(currentCar.getId()).isEqualTo(expectedCar.getId());

    }

    @Test
    void selectAvailableCars_When_Success() {
        List<Car> currentList = carRepository.selectAvailableCars();

        assertNotNull(currentList);
        assertEquals(1, currentList.size());
    }

    @Test
    void save_When_Success() {
        Car currentCar = carRepository.save(expectedCar);

        assertNotNull(currentCar);
        assertThat(currentCar.getId()).isEqualTo(expectedCar.getId());
    }

    @Test
    void delete_When_Success() {
        carRepository.delete(expectedCar);

        Car currentCar = carRepository.findById(1L).orElse(null);

        assertNull(currentCar);

    }


}
