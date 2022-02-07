package com.example.RestApiApplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Exceptions.CarNotFoundException;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Repositories.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
  @Autowired
  private CarRepository carRepository;

  @Autowired
  private ClientService clientService;

  public Car create(Car car, Long clientId) throws ClientNotFoundException {
    Car findedCar = this.carRepository.findByStateNumber(car.getStateNumber());

    if (findedCar == null) {
      Car newCar = new Car(
        car.getModel(),
        car.getStateNumber(),
        this.clientService.getById(clientId)
      );

      return this.carRepository.save(newCar); 
    }
    
    findedCar.setModel(car.getModel());
    findedCar.setClient(this.clientService.getById(clientId));

    return this.carRepository.save(findedCar);
  }

  public Car getById(Long id) throws CarNotFoundException {
    return this.carRepository
        .findById(id)
        .orElseThrow(() -> new CarNotFoundException());
  }

  public List<Car> getAll() {
    return this.carRepository.findAll();
  }

  public Car update(Car car, Long carId, Long clientId) 
  throws CarNotFoundException, ClientNotFoundException {
    Car foundCar = this.carRepository
        .findById(carId)
        .orElseThrow(() -> new CarNotFoundException());

    foundCar.setModel(car.getModel());
    foundCar.setStateNumber(car.getStateNumber());
    foundCar.setClient(this.clientService.getById(clientId));

    return this.carRepository.save(foundCar);
  }

  public Long detele(Long id) {
    this.carRepository.deleteById(id);
    return id;
  }

  public List<Car> getClientCars(Long clientId) {
    return this.getAll()
      .stream()
      .filter(car -> car.getClient().getId() == clientId)
      .collect(Collectors.toList());
  }

}
