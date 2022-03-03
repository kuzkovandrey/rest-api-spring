package com.example.RestApiApplication.Models;

public class ApiCarModel {
  private String model;

  private String stateNumber;

  ApiCarModel() {}

  public ApiCarModel(String model, String stateNumber) {
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