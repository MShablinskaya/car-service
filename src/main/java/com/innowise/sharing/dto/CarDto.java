package com.innowise.sharing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
    private String brand;
    private String model;
    private String color;
    private Integer releaseYear;
    private String registrationNumber;
    private UserDto ownerId;
    private Boolean availability;
}
