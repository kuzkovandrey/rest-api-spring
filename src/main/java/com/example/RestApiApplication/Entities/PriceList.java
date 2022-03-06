package com.example.RestApiApplication.Entities;

import java.util.List;

import javax.persistence.*;

import com.example.RestApiApplication.Constants.ColumnName;
import com.example.RestApiApplication.Constants.TableName;

@Entity
@Table(name = TableName.PRICE_LISTS)
public class PriceList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ColumnName.ID, nullable = false, updatable = false)
  private Long id;

  @Column(name = ColumnName.DESCRIPTION, nullable = false)
  private String description;

  @Column(name = ColumnName.COST, nullable = false)
  private float cost;

  @OneToMany(mappedBy = ColumnName.EMPLOYEE, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  private List<Order> orders;

  PriceList() {}

  PriceList(String description, float cost) {
    this.description = description;
    this.cost = cost;
  }

  public Long getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public float getCost() {
    return this.cost;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }
}
