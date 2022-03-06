package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Exceptions.CarNotFoundException;
//import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
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
  public ResponseEntity<Car> getById(@PathVariable("id") Long id) {
    try {
      Car findCar = this.carService.getById(id);

      return new ResponseEntity<>(findCar, HttpStatus.OK);
    } catch (CarNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @PostMapping(ApiController.ID_PATH)
  // public ResponseEntity<Car> create(
  //   @RequestBody Car car,
  //   @PathVariable("id") Long clientId
  // ) {
  //   try {
  //     Car createdCar = this.carService.create(car, clientId);

  //     return new ResponseEntity<Car>(createdCar, HttpStatus.CREATED);
  //   } catch (Exception e) {
  //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //   }
  // }

  // @PatchMapping(ApiController.ID_PATH)
  // public ResponseEntity<Car> update(
  //   @RequestBody Car car,
  //   @PathVariable("id") Long carId,
  //   @RequestParam(name = "clientId") Long clientId
  // ) {
  //   try {
  //     Car updatedCar = this.carService.update(car, carId, clientId);

  //     return new ResponseEntity<Car>(updatedCar, HttpStatus.CREATED);
  //   } catch (CarNotFoundException e) {
  //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //   } catch (ClientNotFoundException e) {
  //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //   } catch (Exception e) {
  //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //   }
  // }

  // @DeleteMapping(ApiController.ID_PATH)
  // public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
  //   this.carService.detele(id);
  //   return new ResponseEntity<Long>(id, HttpStatus.OK);
  // }

  
  // @GetMapping(ApiController.CLIENTS + ApiController.ID_PATH)
  // public ResponseEntity<List<Car>> getClientCars(
  //   @PathVariable("id") Long clientId
  // ) {
  //   List<Car> clientCars = this.carService.getClientCars(clientId);

  //   return new ResponseEntity<List<Car>>(clientCars, HttpStatus.OK);
  // }
}
