package com.innowise.sharing.service.util;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.Order;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.enums.Action;
import com.innowise.sharing.enums.State;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class OrderTestUtil {
    public static final Long ID = 1L;
    public static final UserDto USER_DTO = UserTestUtil.createUserDto();
    public static final User USER = UserTestUtil.createUser();
    public static final Car CAR = CarTestUtil.createCar();
    public static final CarDto CAR_DTO = CarTestUtil.createCarDto();
    public static final State STATE = State.RESERVED;
    public static final Timestamp BOOKING_DATE = Timestamp.from(Instant.now());
    public static final Timestamp RETURN_DATE = Timestamp.from(Instant.now());


    public Order createOrder() {
        return new Order()
                .setId(ID)
                .setCustomer(USER)
                .setCar(CAR)
                .setState(STATE)
                .setBookingDate(BOOKING_DATE)
                .setReturnDate(RETURN_DATE);
    }

    public OrderDto createOrderDto() {
        return OrderDto.builder()
                .id(ID)
                .customer(USER_DTO)
                .car(CAR_DTO)
                .state(STATE.toString())
                .bookingDate(BOOKING_DATE)
                .returnDate(RETURN_DATE)
                .build();
    }


}
