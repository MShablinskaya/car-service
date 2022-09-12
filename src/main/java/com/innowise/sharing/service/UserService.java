package com.innowise.sharing.service;

import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface UserService {
    UserDto getUserDtoByEmail(@Valid String email);

    User getUserByEmail(@Valid String email);
}
