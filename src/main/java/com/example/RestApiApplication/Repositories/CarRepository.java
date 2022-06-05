package com.example.RestApiApplication.Repositories;

import java.util.List;
import com.example.RestApiApplication.Entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
  Car findByStateNumber(String stateNumber);
  Car findByStateNumberAndModel(String stateNumber, String model);
  List<Car> findAll();
}
