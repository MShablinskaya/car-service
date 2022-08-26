package com.innowise.sharing.service;

import com.innowise.sharing.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
