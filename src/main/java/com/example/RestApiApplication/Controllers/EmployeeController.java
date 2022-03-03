package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Entities.Employee;
import com.example.RestApiApplication.Exceptions.EmployeeNotFoundException;
import com.example.RestApiApplication.Services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiController.EMPLOYEES)
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<Employee> create(@RequestBody Employee employee) {
    try {
      Employee newEmployee = this.employeeService.create(employee);

      return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @GetMapping
  public ResponseEntity<List<Employee>> getAll() {
    return new ResponseEntity<List<Employee>>(
      this.employeeService.getAll(),
      HttpStatus.OK
    ); 
  }

  @GetMapping(ApiController.ID_PATH)
  public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
    try {
      Employee findEmployee = this.employeeService.getById(id);

      return new ResponseEntity<>(findEmployee, HttpStatus.OK);
    } catch (EmployeeNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping(ApiController.ID_PATH)
  public ResponseEntity<Employee> update(
    @RequestBody Employee employee,
    @PathVariable("id") Long id
  ) {
    try {
      Employee updatedEmployee = this.employeeService.update(employee, id);

      return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.CREATED);
    } catch (EmployeeNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(ApiController.ID_PATH)
  public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
    this.employeeService.delete(id);
    
    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }
  
}
