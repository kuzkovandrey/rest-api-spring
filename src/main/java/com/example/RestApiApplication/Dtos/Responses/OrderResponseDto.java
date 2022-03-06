package com.example.RestApiApplication.Dtos.Responses;

import java.util.Date;

public class OrderResponseDto {
  private Long id;

  private Long clientId;

  private Long employeeId;

  private Long priceListId;

  private Date createdAt;

  private float price;

  OrderResponseDto() {}

  public OrderResponseDto(
    Long id,
    Long clientId, 
    Long employeeId, 
    Long priceListId,
    float price,
    Date createdAt
  ) {
    this.id = id;
    this.clientId = clientId;
    this.employeeId = employeeId;
    this.priceListId = priceListId;
    this.price = price;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getClientId() {
    return this.clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
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

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
