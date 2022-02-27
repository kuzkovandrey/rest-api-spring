package com.example.RestApiApplication.Entities;

import javax.persistence.*;

import com.example.RestApiApplication.Constants.ColumnName;
import com.example.RestApiApplication.Constants.TableName;

@Entity
@Table(name = TableName.EMPLOYEES)
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ColumnName.ID, nullable = false, updatable = false)
  private Long id;

  @Column(name = ColumnName.NAME)
  private String name;

  @Column(name = ColumnName.NAME)
  private float rate;

  Employee() {}

  Employee(String name, float rate) {
    this.name = name;
    this.rate = rate;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public float getRate() {
    return this.rate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRate(float rate) {
    this.rate = rate;
  }

}
