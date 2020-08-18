package com.example.graphql.filter;

import lombok.Data;

@Data
public class UserFilter {

    private String dni;
    private String username;
    private Long page;
    private Long limit;
}
