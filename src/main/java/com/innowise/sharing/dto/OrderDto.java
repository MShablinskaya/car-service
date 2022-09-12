package com.innowise.sharing.dto;

import com.innowise.sharing.valid.group.OnCreateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;

    @NotNull(groups = OnCreateGroup.class)
    @Valid
    private UserDto customer;

    @NotNull(groups = OnCreateGroup.class)
    @Valid
    private CarDto car;

    private String state;

    @FutureOrPresent(message = "You cannot reserve a car retroactively.")
    private Timestamp bookingDate;

    @FutureOrPresent(message = "You cannot return a car retroactively.")
    private Timestamp returnDate;

    private List<String> actions;
}
