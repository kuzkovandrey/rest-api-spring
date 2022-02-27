package com.example.RestApiApplication.Entities;

import java.io.Serializable;

import javax.persistence.*;

import com.example.RestApiApplication.Constants.ColumnName;
import com.example.RestApiApplication.Constants.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = TableName.CARS)
public class Car implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ColumnName.ID, nullable = false, updatable = false)
  private Long id;

  @Column(name = ColumnName.MODEL)
  private String model;

  @Column(name = ColumnName.STATE_NUMBER)
  private String stateNumber;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = ColumnName.CLIENT_ID, nullable = false)
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  private Client client;

  public Car() {}

  public Car(String model, String stateNumber, Client client) {
    this.model = model;
    this.stateNumber = stateNumber;
    this.client = client;
  }

  public Long getId() {
    return this.id;
  }

  public String getModel() {
    return this.model;
  }

  public String getStateNumber() {
    return this.stateNumber;
  }

  public Client getClient() {
    return this.client;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setStateNumber(String stateNumber) {
    this.stateNumber = stateNumber;
  }

  public void setClient(Client client) {
    this.client = client;
  }

}
