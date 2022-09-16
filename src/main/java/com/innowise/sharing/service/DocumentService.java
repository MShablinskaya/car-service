package com.innowise.sharing.service;

import com.innowise.sharing.dto.DocumentDto;

public interface DocumentService {
    DocumentDto getBySerialNumber(String serialNumber);
}
