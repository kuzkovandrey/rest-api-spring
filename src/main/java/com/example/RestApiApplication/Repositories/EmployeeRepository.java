package com.example.RestApiApplication.Repositories;

import com.example.RestApiApplication.Entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  Employee findByName(String name);
  List<Employee> findAll();
}
