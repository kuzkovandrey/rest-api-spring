package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.PathVariables;
import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Dtos.Responses.ErrorResponse;
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
  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Employee employee) {
    try {
      Employee newEmployee = this.employeeService.create(employee);

      return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    } catch (Exception e) {
      return ErrorResponse.getBadRequestError();
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
  public ResponseEntity<?> getById(@PathVariable(PathVariables.ID) Long id) {
    try {
      Employee findEmployee = this.employeeService.getById(id);

      return new ResponseEntity<Employee>(findEmployee, HttpStatus.OK);
    } catch (EmployeeNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }

  @PatchMapping(ApiController.ID_PATH)
  public ResponseEntity<?> update(
    @RequestBody Employee employee,
    @PathVariable("id") Long id
  ) {
    try {
      Employee updatedEmployee = this.employeeService.update(employee, id);

      return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.CREATED);
    } catch (EmployeeNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }

  @DeleteMapping(ApiController.ID_PATH)
  public ResponseEntity<?> delete(@PathVariable(PathVariables.ID) Long id) {
    try {
      this.employeeService.delete(id);
    
      return new ResponseEntity<Long>(id, HttpStatus.OK);
    } catch (EmployeeNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }
  
}
