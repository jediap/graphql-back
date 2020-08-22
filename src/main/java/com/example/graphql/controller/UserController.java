package com.example.graphql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "user")
    public String getCities() {
        return "hola test";
    }

}