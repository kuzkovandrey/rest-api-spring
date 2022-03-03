package com.example.RestApiApplication.Exceptions;

import com.example.RestApiApplication.Constants.ErrorMessage;

public class ClientNotFoundException extends NotFoundException {
  public ClientNotFoundException() {
    super(ErrorMessage.CLIENT_NOT_FOUND);
  }
}
