package com.innowise.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    @NotBlank(message = "You need to specify the valid license number")
    private String licenseNumber;

    @Future(message = "The expiration date cannot be older than now")
    private Timestamp expirationDate;
}
