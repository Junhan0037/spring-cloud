package com.userservice.service;

import com.userservice.domain.User;
import com.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<User> getUserByAll();
    UserDto getUserDetailsByEmail(String userName);
}
