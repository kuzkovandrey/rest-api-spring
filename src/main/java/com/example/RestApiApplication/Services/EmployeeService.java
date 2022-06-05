package com.example.RestApiApplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.RestApiApplication.Entities.Employee;
import com.example.RestApiApplication.Exceptions.EmployeeNotFoundException;
import com.example.RestApiApplication.Repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  private Employee findByName(String name) {
    return this.employeeRepository.findByName(name);
  }

  public Employee create(Employee employee) {
    Employee empl = this.findByName(employee.getName());

    if (empl == null) return this.employeeRepository.save(employee);

    empl.setIsRemoved(false);

    return empl;
    //return this.employeeRepository.save(employee);
  }

  public Employee getById(Long id) throws EmployeeNotFoundException {
    return this.employeeRepository
      .findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException());
  }

  public List<Employee> getAll() {
    return this.employeeRepository
      .findAll()
      .stream()
      .filter(employee -> !employee.getIsRemoved())
      .collect(Collectors.toList());
  }

  public Employee update(Employee empl, Long id) throws EmployeeNotFoundException {
    Employee employee = this.getById(id);

    employee.setName(empl.getName());
    employee.setRate(empl.getRate());

    return this.employeeRepository.save(employee);
  }

  public Long delete(Long id) throws EmployeeNotFoundException {
    Employee employee = this.getById(id);

    employee.setIsRemoved(true);

    this.update(employee, id);

    return id;
  }
}
