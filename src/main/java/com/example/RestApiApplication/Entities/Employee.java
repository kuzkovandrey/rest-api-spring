package com.example.RestApiApplication.Entities;

import java.util.List;

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

  @Column(name = ColumnName.EMPLOYEE_NAME, nullable = false)
  private String name;

  @Column(name = ColumnName.RATE, nullable = false)
  private float rate;

  @OneToMany(
    mappedBy = ColumnName.EMPLOYEE, 
    cascade = { CascadeType.MERGE, CascadeType.PERSIST }
  )
  private List<Order> orders;

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
