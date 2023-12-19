package com.example.demo.exception;

public class RestApiServiceException extends AbstractBusinessLogicException {

  private final int code;
  private final String message;

  public RestApiServiceException() {
    this.code = 500;
    this.message = "Failed with getting result from remote service";
  }

  public RestApiServiceException(final Integer code, final String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
