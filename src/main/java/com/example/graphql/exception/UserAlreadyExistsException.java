package com.example.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserAlreadyExistsException extends RuntimeException implements GraphQLError {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("message", this.getMessage());
        return customAttributes;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}
