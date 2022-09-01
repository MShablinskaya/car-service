package com.innowise.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;

    private UserDto customer;

    private CarDto car;

    @NotBlank(message = "Can't be empty")
    private String state;

    @FutureOrPresent(message = "You cannot reserve a car retroactively")
    private Timestamp bookingDate;

    @FutureOrPresent(message = "You cannot return a car retroactively")
    private Timestamp returnDate;
}
