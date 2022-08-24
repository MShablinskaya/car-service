package com.innowise.sharing.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class DocumentDto {
    private String licenseNumber;
    private Timestamp expirationDate;
}
