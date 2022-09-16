package com.innowise.sharing.exception;

import javax.persistence.EntityNotFoundException;

public class CarEntityNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Car entity with id:%s not found.";

    public CarEntityNotFoundException(Long carId) {
        super(String.format(DEFAULT_MESSAGE, carId));
    }
}
