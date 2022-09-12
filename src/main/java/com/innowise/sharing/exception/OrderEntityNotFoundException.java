package com.innowise.sharing.exception;

import javax.persistence.EntityNotFoundException;

public class OrderEntityNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Order with ID: %s is not found";

    public OrderEntityNotFoundException(Long orderId) {
        super(String.format(DEFAULT_MESSAGE, orderId));
    }
}
