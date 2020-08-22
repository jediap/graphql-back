package com.example.graphql.controller;

import com.example.graphql.entity.User;
import com.example.graphql.service.UserService;
import com.example.graphql.vo.UserCreationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUsers(@RequestBody UserCreationVO userCreationVO) {
        return userService.createUser(userCreationVO);
    }

}