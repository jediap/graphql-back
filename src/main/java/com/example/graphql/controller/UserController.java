package com.example.graphql.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(value = "user")
    public String createUsers() {
        return "new user";
    }

}