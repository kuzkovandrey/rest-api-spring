package com.example.RestApiApplication.Dtos.Requests;

public class OrderRequestDto {
  private ClientRequestDto client;

  private CarRequestDto car;

  private Long employeeId;

  private Long priceListId;

  private float price;

  OrderRequestDto() {}

  public OrderRequestDto(
    ClientRequestDto client, 
    CarRequestDto car, 
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

  public ClientRequestDto getClient() {
    return this.client;
  }

  public void setClient(ClientRequestDto client) {
    this.client = client;
  }

  public CarRequestDto getCar() {
    return this.car;
  }

  public void setCar(CarRequestDto car) {
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
