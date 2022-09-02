package com.innowise.sharing.service;

import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.User;

public interface UserService {
    UserDto getUserDtoByEmail(String email);

    User getUserByEmail(String email);
}
