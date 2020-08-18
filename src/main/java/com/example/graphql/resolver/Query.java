package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.filter.UserFilter;
import com.example.graphql.repository.UserRepository;
import com.example.graphql.type.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    /**public List<User> usersByDni(String dni){
        return userRepository.findUsersByDni(dni);
    }*/

    public List<User> users(UserFilter filter){
        return userRepository.findUsers(filter);
    }
}
