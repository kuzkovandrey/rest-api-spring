package com.example.RestApiApplication.Models;

public class ApiOrderModel {
  private ApiClientModel client;

  private ApiCarModel car;

  private Long employeeId;

  private Long priceListId;

  private float price;

  ApiOrderModel() {}

  public ApiOrderModel(
    ApiClientModel client, 
    ApiCarModel car, 
    Long employeeId, 
    Long priceListId, 
    float price
  ) {
    this.client = client;
    this.car = car;
    this.employeeId = employeeId;
    this.priceListId = priceListId;
    this.price = price;
  }

  public ApiClientModel getClient() {
    return this.client;
  }

  public void setClient(ApiClientModel client) {
    this.client = client;
  }

  public ApiCarModel getCar() {
    return this.car;
  }

  public void setCar(ApiCarModel car) {
    this.car = car;
  }

  public Long getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Long getPriceListId() {
    return this.priceListId;
  }

  public void setPriceListId(Long priceListId) {
    this.priceListId = priceListId;
  }

  public float getPrice() {
    return this.price;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
