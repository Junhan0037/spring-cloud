package com.userservice.service;

import com.userservice.domain.User;
import com.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<User> getUserByAll();
}
