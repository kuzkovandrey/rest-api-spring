package com.example.RestApiApplication.Exceptions;

import com.example.RestApiApplication.Constants.ErrorMessage;

public class EmployeeNotFoundException extends NotFoundException {
  public EmployeeNotFoundException() {
    super(ErrorMessage.EMPLOYEE_NOT_FOUND);
  }
}
