package com.idrica.goaigua.controller;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.idrica.goaigua.controller.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author globalomnium
 *
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ControllerHandlerAdvice {

  /**
   * @param request {@link HttpServletRequest}
   * @param exception {@link Exception}
   * @return responseEntity {@link ResponseEntity}
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidParameterException.class)
  @ResponseBody
  public ResponseEntity<Object> handleNotValidEx(HttpServletRequest request, Exception exception) {

    ErrorResponse errorResponse = getErrorResponse(exception);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  /**
   * @param request {@link HttpServletRequest}
   * @param exception {@link Exception}
   * @return responseEntity {@link ResponseEntity}
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<Object> handleEx(HttpServletRequest request, Exception exception) {

    ErrorResponse errorResponse = getErrorResponse(exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

  private ErrorResponse getErrorResponse(Exception exception) {

    log.error(exception.getMessage(), exception);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(exception.getMessage());
    return errorResponse;
  }
}
