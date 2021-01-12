package com.idrica.goaigua.service.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idrica.goaigua.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<UserResolver> {

    private UserRepository userRepository;

    public UserResolver(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
}
