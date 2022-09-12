package com.innowise.sharing.exception;

import javax.persistence.EntityNotFoundException;

public class DocumentEntityNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Document not Found";

    public DocumentEntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
