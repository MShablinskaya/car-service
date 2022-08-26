package com.innowise.sharing.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Builder
public class DocumentDto {
    private String licenseNumber;
    private Timestamp expirationDate;
}
