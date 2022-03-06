package com.example.RestApiApplication.Dtos.Responses;

public class ApiError {
  private String message;

  ApiError() {}

  public ApiError(String message) {
    this.message = message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
