package com.example.graphql.repository;


import com.example.graphql.entity.User;

import com.example.graphql.filter.UserFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserRepository {

    private static AtomicLong ID_GENERATOR = new AtomicLong(1000);
    private List<User> users = new ArrayList<>();

    public UserRepository() {

        User user1 = new User(ID_GENERATOR.getAndIncrement(), "1", "test_1");
        User user2 = new User(ID_GENERATOR.getAndIncrement(), "2", "test_2");
        User user3 = new User(ID_GENERATOR.getAndIncrement(), "3", "test_3");

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public User findUser(Long id) {
        return users.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public List<User> findUsers(UserFilter filter) {
        Stream<User> stream = users.stream();
        if (filter != null) {
            stream = stream.filter(b -> b.getDni().matches(filter.getDni())).filter(b -> b.getUsername().matches(filter.getUsername()));
        }
        return stream.collect(Collectors.toList());
    }

    public User addUser(String dni, String username){
        User user = new User(ID_GENERATOR.getAndIncrement(), dni, username);
        users.add(user);
        return user;
    }
}
