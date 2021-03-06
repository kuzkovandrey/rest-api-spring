package com.example.RestApiApplication.Services;

import java.util.List;

import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Exceptions.CarNotFoundException;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Repositories.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
  private final CarRepository carRepository;

  private final ClientService clientService;

  @Autowired
  public CarService(CarRepository carRepository, ClientService clientService) {
    this.carRepository = carRepository;
    this.clientService = clientService;
  }

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
    Car foundCar = this.getById(carId);
    
    foundCar.setModel(car.getModel());
    foundCar.setStateNumber(car.getStateNumber());
    foundCar.setClient(this.clientService.getById(clientId));

    return this.carRepository.save(foundCar);
  }

  public Long detele(Long id) {
    this.carRepository.deleteById(id);
    return id;
  }
}
