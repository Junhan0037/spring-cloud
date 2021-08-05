package com.userservice.controller;

import com.userservice.domain.User;
import com.userservice.dto.UserDto;
import com.userservice.service.UserService;
import com.userservice.vo.Greeting;
import com.userservice.vo.RequestUser;
import com.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {
        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ResponseUser>> getUser() {
        Iterable<User> userList = userService.getUserByAll();

        List<ResponseUser> result  = new ArrayList<>();
        userList.forEach(v -> {
            result.add(modelMapper.map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

}
