package com.secondservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second service.";
    }

}
