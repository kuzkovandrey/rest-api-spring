package com.example.RestApiApplication.Exceptions;

import com.example.RestApiApplication.Constants.ErrorMessage;

public class ClientNotFoundException extends Exception {
  public ClientNotFoundException() {
    super(ErrorMessage.CLIENT_NOT_FOUND);
  }
}
