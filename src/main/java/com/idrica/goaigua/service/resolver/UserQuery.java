package com.idrica.goaigua.service.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idrica.goaigua.criteria.UserCriteria;
import com.idrica.goaigua.domain.entity.User;
import com.idrica.goaigua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

    private UserRepository userRepository = new UserRepository();

    public List<User> users(UserCriteria filter){
        return userRepository.findUsers(filter);
    }
}
