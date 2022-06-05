package com.example.RestApiApplication.Repositories;

import com.example.RestApiApplication.Entities.Employee;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  Employee findByName(String name);
  List<Employee> findAll();
}
