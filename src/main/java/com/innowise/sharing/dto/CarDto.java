package com.innowise.sharing.dto;

import com.innowise.sharing.validation.group.OnCreateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotNull(groups = OnCreateGroup.class, message = "You need to specify car id.")
    @Min(value = 1, groups = OnCreateGroup.class, message = "You need to specify correct car id.")
    private Long id;

    @Pattern(regexp = "[А-Яа-яё\\p{Alpha}]{1,20}", message = "You need to specify the correct brand.")
    private String brand;

    @Pattern(regexp = "[\\dА-Яа-яё\\p{Alpha}-]{1,20}", message = "You need to specify the correct model.")
    private String model;

    @Pattern(regexp = "[А-Яа-яё\\p{Alpha}-]{1,20}", message = "You need to specify the correct color.")
    private String color;

    @Min(1990)
    @Max(2022)
    private Integer releaseYear;

    @Pattern(regexp = "\\d{4} [A-Z]{2}-\\d",
            message = "You need to specify the correct registration number (example:0000 AA-0).")
    private String registrationNumber;

    private UserDto ownerId;

    private boolean availability;
}
