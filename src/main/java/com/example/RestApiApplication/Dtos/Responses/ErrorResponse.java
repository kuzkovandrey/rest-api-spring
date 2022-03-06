package com.example.RestApiApplication.Dtos.Responses;

import com.example.RestApiApplication.Constants.ErrorMessage;
import com.example.RestApiApplication.Exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
  public static ResponseEntity<ApiError> getNotFoundError(NotFoundException e) {
    return new ResponseEntity<ApiError>(
      new ApiError(e.getMessage()), 
      HttpStatus.NOT_FOUND
    );
  } 

  public static ResponseEntity<ApiError> getServerError() {
    return new ResponseEntity<ApiError>(
      new ApiError(ErrorMessage.SERVER_ERROR), 
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  public static ResponseEntity<ApiError> getBadRequestError() {
    return new ResponseEntity<ApiError>(
      new ApiError(ErrorMessage.BAD_REQUEST), 
      HttpStatus.BAD_REQUEST
    );
  }
}
