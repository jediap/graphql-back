package com.idrica.goaigua.controller.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception generic to control all cases of errors.
 */
@Getter
@Setter
public class GenericException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private final Integer code;

  private final String message;

  /**
   * @param message of exception
   * @param code of exception
   */
  public GenericException(String message, Integer code) {

    this.message = message;
    this.code = code;
  }

}
