package com.innowise.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;

    @NotBlank(message = "You need to specify the correct brand.")
    private String brand;

    @NotBlank(message = "You need to specify the correct model.")
    private String model;

    @NotBlank(message = "You need to specify the correct color.")
    private String color;

    @Min(1990)
    @Max(2022)
    @NotNull
    private Integer releaseYear;

    @NotBlank(message = "You need to specify the correct registration number.")
    private String registrationNumber;

    private UserDto ownerId;
    private Boolean availability;
}
