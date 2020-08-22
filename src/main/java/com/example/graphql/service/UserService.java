package com.example.graphql.service;

import com.example.graphql.entity.User;
import com.example.graphql.repository.UserRepository;
import com.example.graphql.vo.UserCreationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationVO userCreationVO){
        return userRepository.addUser(userCreationVO.getDni(), userCreationVO.getUsername());
    }
}
