package com.innowise.sharing.service;

import com.innowise.sharing.entity.User;

public interface UserService {
    User getUserByEmail(String email);
}
