package com.example.graphql.exception.handler;

import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GraphqlErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::getGraphQLError).collect(Collectors.toList());
    }

    private GraphQLError getGraphQLError(GraphQLError error) {
        return new GenericGraphQLError(error.getMessage());
    }
}
