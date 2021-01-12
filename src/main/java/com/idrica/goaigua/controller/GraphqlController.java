package com.idrica.goaigua.controller;

import com.coxautodev.graphql.tools.SchemaParser;
import com.idrica.goaigua.service.mutation.UserMutation;
import com.idrica.goaigua.service.resolver.UserQuery;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class GraphqlController extends SimpleGraphQLServlet {


    public GraphqlController() {
        super(buildSchema());
    }

    @NotNull
    private static GraphQLSchema buildSchema() {
        return SchemaParser
                .newParser()
                .file("user.graphqls")
                .resolvers(
                        new UserMutation(),
                        new UserQuery())
                .build()
                .makeExecutableSchema();
    }
}