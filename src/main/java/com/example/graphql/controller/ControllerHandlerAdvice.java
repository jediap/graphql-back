package com.example.graphql.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;

/**
 * @author globalomnium
 */
@ControllerAdvice
@ResponseBody
public class ControllerHandlerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHandlerAdvice.class);

  /**
   * @param request   {@link HttpServletRequest}
   * @param exception {@link Exception}
   * @return responseEntity {@link ResponseEntity}
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidParameterException.class)
  @ResponseBody
  public ResponseEntity<Object> handleNotValidEx(HttpServletRequest request, Exception exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  /**
   * @param request   {@link HttpServletRequest}
   * @param exception {@link Exception}
   * @return responseEntity {@link ResponseEntity}
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<Object> handleEx(HttpServletRequest request, Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }
}
