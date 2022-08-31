package com.innowise.sharing.kafka.mapper;

public enum AvroRecordField {
    ID,
    BRAND,
    MODEL,
    COLOR,
    RELEASE_YEAR,
    REGISTRATION_NUMBER,
    AVAILABILITY,
    USER,
    FIRST_NAME,
    LAST_NAME,
    EMAIL,
    DOCUMENT,
    LICENSE_NUMBER,
    EXPIRATION_DATE,
    DOCUMENT_TYPE;

    public String getField() {
        return this.name().toLowerCase();
    }
}
