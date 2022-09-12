package com.innowise.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    @Pattern(regexp = "\\d{7}", message = "You need to specify the valid license number (7-number value)")
    private String licenseNumber;

    @Future(message = "The expiration date cannot be older than now")
    private Timestamp expirationDate;
}
