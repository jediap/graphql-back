package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.repository.UserRepository;
import com.example.graphql.type.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    public User addUser(String dni, String username){
        return userRepository.addUser(dni, username);
    }
}
