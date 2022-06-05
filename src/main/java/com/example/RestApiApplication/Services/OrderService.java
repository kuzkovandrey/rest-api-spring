package com.example.RestApiApplication.Services;

import java.util.List;

import com.example.RestApiApplication.Dtos.Requests.CarRequestDto;
import com.example.RestApiApplication.Dtos.Requests.ClientRequestDto;
import com.example.RestApiApplication.Dtos.Requests.OrderRequestDto;
import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Entities.Client;
import com.example.RestApiApplication.Entities.Employee;
import com.example.RestApiApplication.Entities.Order;
import com.example.RestApiApplication.Entities.PriceList;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Exceptions.EmployeeNotFoundException;
import com.example.RestApiApplication.Exceptions.OrderNotFoundException;
import com.example.RestApiApplication.Exceptions.PriceListNotFoundException;
import com.example.RestApiApplication.Repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private PriceListService priceListService;

  @Autowired
  private ClientService clientService;

  @Autowired
  private CarService carService;

  @Autowired
  private OrderRepository orderRepository;

  @Transactional(rollbackFor = { Exception.class })
  public Order create(OrderRequestDto order)
      throws ClientNotFoundException, EmployeeNotFoundException, PriceListNotFoundException {
    Client client = this.createClient(order.getClient());
    Car car = this.createCar(order.getCar(), client);
    Employee employee = this.employeeService.getById(order.getEmployeeId());

    PriceList priceList = this.priceListService.getById(order.getPriceListId());
    float price = order.getPrice();

    Order createdOrder = new Order(client, employee, priceList, price);

    return this.orderRepository.save(createdOrder);
  }

  public Order getById(Long id) throws OrderNotFoundException{
    return this.orderRepository
      .findById(id)
      .orElseThrow(() -> new OrderNotFoundException());
  }

  public List<Order> getAll() {
    return this.orderRepository.findAll();
  }

  public Long delete(Long id) {
    this.orderRepository.deleteById(id);
    return id;
  }

  private Client createClient(ClientRequestDto client) {
    return this.clientService.create(
      new Client(
        client.getEmail(),
        client.getName()
      )
    );
  }

  private Car createCar(CarRequestDto car, Client client) 
    throws ClientNotFoundException {
    return this.carService.create(
      new Car(
        car.getModel(),
        car.getStateNumber(),
        client
      ),
      client.getId()
    );
  }
}
