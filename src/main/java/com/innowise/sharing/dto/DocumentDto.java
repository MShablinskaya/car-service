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

    @NotBlank
    private String licenseNumber;

    @Future(message = "The expiration date cannot be in tne past")
    private Timestamp expirationDate;
}
