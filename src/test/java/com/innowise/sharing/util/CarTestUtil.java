package com.innowise.sharing.util;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.User;

public class CarTestUtil {
    public static final Long ID = 1L;
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String COLOR = "color";
    public static final int YEAR = 2000;
    public static final String NUMBER = "number";
    public static final User OWNER = UserTestUtil.createUser();
    public static final UserDto OWNER_DTO = UserTestUtil.createUserDto();
    public static final boolean AVAILABILITY = true;

    public static Car createCar() {
        return new Car()
                .setId(ID)
                .setBrand(BRAND)
                .setModel(MODEL)
                .setColor(COLOR)
                .setReleaseYear(YEAR)
                .setRegistrationNumber(NUMBER)
                .setOwner(OWNER)
                .setAvailability(AVAILABILITY);
    }

    public static CarDto createCarDto() {
        return CarDto.builder()
                .id(ID)
                .brand(BRAND)
                .model(MODEL)
                .color(COLOR)
                .releaseYear(YEAR)
                .registrationNumber(NUMBER)
                .ownerId(OWNER_DTO)
                .availability(AVAILABILITY)
                .build();
    }
}
