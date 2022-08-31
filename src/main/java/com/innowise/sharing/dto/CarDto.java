package com.innowise.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
    @PastOrPresent(message = "The year of release cannot be in the future")
    private Integer releaseYear;
    @NotBlank
    private String registrationNumber;
    private UserDto ownerId;
    private Boolean availability;
}
