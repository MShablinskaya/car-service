package com.innowise.sharing.dto;

import com.innowise.sharing.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class CarDto {
    private String brand;
    private String model;
    private Timestamp releaseYear;
    private String registrationNumber;
    private User ownerId;
    private Boolean availability;
}
