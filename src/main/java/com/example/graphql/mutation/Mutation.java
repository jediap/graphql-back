package com.example.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.entity.User;
import com.example.graphql.repository.UserRepository;
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
