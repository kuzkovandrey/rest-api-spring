package com.example.RestApiApplication.Services;

import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Entities.Client;
import com.example.RestApiApplication.Entities.Employee;
import com.example.RestApiApplication.Entities.Order;
import com.example.RestApiApplication.Entities.PriceList;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Exceptions.EmployeeNotFoundException;
import com.example.RestApiApplication.Exceptions.PriceListNotFoundException;
import com.example.RestApiApplication.Models.ApiCarModel;
import com.example.RestApiApplication.Models.ApiClientModel;
import com.example.RestApiApplication.Models.ApiOrderModel;
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
  public Order create(ApiOrderModel order)
      throws ClientNotFoundException, EmployeeNotFoundException, PriceListNotFoundException {
    Client client = this.createClient(order.getClient());
    Car car = this.createCar(order.getCar(), client);
    Employee employee = this.employeeService.getById(order.getEmployeeId());

    PriceList priceList = this.priceListService.getById(order.getPriceListId());
    float price = order.getPrice();

    Order createdOrder = new Order(client, employee, priceList, price);

    return this.orderRepository.save(createdOrder);
  }

  private Client createClient(ApiClientModel client) {
    return this.clientService.create(
      new Client(
        client.getEmail(),
        client.getEmail()
      )
    );
  }

  private Car createCar(ApiCarModel car, Client client) throws ClientNotFoundException {
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
