package com.example.RestApiApplication.Entities;

import java.util.Date;

import javax.persistence.*;

import com.example.RestApiApplication.Constants.ColumnName;
import com.example.RestApiApplication.Constants.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = TableName.ORDERS)
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ColumnName.ID, nullable = false, updatable = false)
  private Long id;

  @CreationTimestamp
  @Column(name = ColumnName.CREATED_AT, nullable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = ColumnName.CLIENT_ID, nullable = false)
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = ColumnName.EMPLOYEE_ID, nullable = false)
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  private Employee employee;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = ColumnName.PRICE_LIST_ID, nullable = false)
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  private PriceList priceList;

  @Column(name = ColumnName.PRICE, nullable = false)
  private float price;

//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = ColumnName.CAR_ID, nullable = false)
//  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//  private Car car;

  private String carInfo;

  public Order() {}

  public Order(
    Client client,
    String carInfo,
    Employee employee, 
    PriceList priceList, 
    float price
  ) {
    this.client = client;
    this.carInfo = carInfo;
    this.employee = employee;
    this.priceList = priceList;
    this.price = price;
  }

  public Long getId() {
    return this.id;
  }

  public Client getClient() {
    return this.client;
  }

  public String getCarInfo() {
    return this.carInfo;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public PriceList getPriceList() {
    return this.priceList;
  }

  public float getPrice() {
    return this.price;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public void setCarInfo(String carInfo) {
    this.carInfo = carInfo;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public void setPriceList(PriceList priceList) {
    this.priceList = priceList;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

}
