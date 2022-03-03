package com.example.RestApiApplication.Exceptions;

import com.example.RestApiApplication.Constants.ErrorMessage;

public class PriceListNotFoundException extends NotFoundException {
  public PriceListNotFoundException() {
    super(ErrorMessage.PRICE_LIST_NOT_FOUND);
  }
}
