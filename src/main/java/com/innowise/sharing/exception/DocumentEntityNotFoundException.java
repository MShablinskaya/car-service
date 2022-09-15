package com.innowise.sharing.exception;

import javax.persistence.EntityNotFoundException;

public class DocumentEntityNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Document with registration number %s not Found";

    public DocumentEntityNotFoundException(String number) {
        super(String.format(DEFAULT_MESSAGE, number));
    }
}
