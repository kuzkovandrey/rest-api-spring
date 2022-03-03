package com.example.RestApiApplication.Dtos.Requests;

public class CarRequestDto {
  private String model;

  private String stateNumber;

  CarRequestDto() {}

  public CarRequestDto(String model, String stateNumber) {
    this.model = model;
    this.stateNumber = stateNumber;
  }

  public String getModel() {
    return this.model;
  }

  public String getStateNumber() {
    return this.stateNumber;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setStateNumber(String stateNumber) {
    this.stateNumber = stateNumber;
  }
}