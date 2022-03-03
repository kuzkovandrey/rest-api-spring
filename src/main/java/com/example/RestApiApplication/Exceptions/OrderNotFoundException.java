package com.example.RestApiApplication.Exceptions;

import com.example.RestApiApplication.Constants.ErrorMessage;

public class OrderNotFoundException extends NotFoundException {
  public OrderNotFoundException() {
    super(ErrorMessage.ORDER_NOT_FOUND);
  }
}
