package com.innowise.sharing.exception;

import javax.persistence.EntityNotFoundException;

public class UserEntityNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "User entity with email:%s not found!";

    public UserEntityNotFoundException(String email) {
        super(String.format(DEFAULT_MESSAGE, email));
    }
}
