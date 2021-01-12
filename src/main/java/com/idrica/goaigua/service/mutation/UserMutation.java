package com.idrica.goaigua.service.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idrica.goaigua.domain.entity.User;
import com.idrica.goaigua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    public User addUser(String dni, String username){
        return userRepository.addUser(dni, username);
    }
}
