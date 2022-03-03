package com.example.RestApiApplication.Exceptions;

import com.example.RestApiApplication.Constants.ErrorMessage;

public class CarNotFoundException extends NotFoundException {
  public CarNotFoundException() {
    super(ErrorMessage.CAR_NOT_FOUND);
  }
}
