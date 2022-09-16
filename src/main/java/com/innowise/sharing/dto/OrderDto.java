package com.innowise.sharing.dto;

import com.innowise.sharing.enums.Action;
import com.innowise.sharing.validation.group.OnCreateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.Instant;
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
    private Instant bookingDate;

    @FutureOrPresent(message = "You cannot return a car retroactively.")
    private Instant returnDate;

    private List<Action> actions;
}
