package com.example.RestApiApplication.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.example.RestApiApplication.Constants.ColumnName;
import com.example.RestApiApplication.Constants.TableName;

@Entity
@Table(name = TableName.CLIENTS)
public class Client implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ColumnName.ID, nullable = false, updatable = false)
  private Long id;

  @Column(name = ColumnName.EMAIL, nullable = false)
  private String email;

  @Column(name = ColumnName.NAME, nullable = false)
  private String name;

  @OneToMany(mappedBy = ColumnName.CLIENT, cascade = CascadeType.ALL)
  private List<Car> cars;

  @OneToMany(
    mappedBy = ColumnName.CLIENT, 
    cascade = { CascadeType.MERGE, CascadeType.PERSIST }
  )
  private List<Order> orders;

  public Client() {}

  public Client(String email, String name) {
    this.email = email;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public List<Car> getCars() {
    return this.cars;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }

}
