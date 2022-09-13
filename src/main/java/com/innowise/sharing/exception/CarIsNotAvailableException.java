package com.innowise.sharing.exception;

public class CarIsNotAvailableException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Car with ID:%s is not available right now.";

    public CarIsNotAvailableException(Long carId) {
        super(String.format(DEFAULT_MESSAGE, carId));
    }
}
