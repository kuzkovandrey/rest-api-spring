package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Constants.PathVariables;
import com.example.RestApiApplication.Dtos.Responses.ErrorResponse;
import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Exceptions.CarNotFoundException;
import com.example.RestApiApplication.Services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiController.CARS)
public class CarController {
  @Autowired
  private CarService carService;

  @GetMapping
  public ResponseEntity<List<Car>> getAll() {
    return new ResponseEntity<List<Car>>(this.carService.getAll(), HttpStatus.OK);
  }

  @GetMapping(ApiController.ID_PATH)
  public ResponseEntity<?> getById(@PathVariable(PathVariables.ID) Long id) {
    try {
      Car findCar = this.carService.getById(id);
      
      return new ResponseEntity<Car>(findCar, HttpStatus.OK);
    } catch (CarNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }
}
