package com.example.RestApiApplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.RestApiApplication.Dtos.Requests.CarRequestDto;
import com.example.RestApiApplication.Dtos.Requests.ClientRequestDto;
import com.example.RestApiApplication.Dtos.Requests.OrderRequestDto;
import com.example.RestApiApplication.Dtos.Responses.OrderInfoDto;
import com.example.RestApiApplication.Dtos.Responses.ShortOrder;
import com.example.RestApiApplication.Entities.Car;
import com.example.RestApiApplication.Entities.Client;
import com.example.RestApiApplication.Entities.Employee;
import com.example.RestApiApplication.Entities.Order;
import com.example.RestApiApplication.Entities.PriceList;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Exceptions.EmployeeNotFoundException;
import com.example.RestApiApplication.Exceptions.OrderNotFoundException;
import com.example.RestApiApplication.Exceptions.PriceListNotFoundException;
import com.example.RestApiApplication.Mappers.OrderMapper;
import com.example.RestApiApplication.Repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  private final EmployeeService employeeService;

  private final PriceListService priceListService;

  private final ClientService clientService;

  private final CarService carService;

  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(
          EmployeeService employeeService,
          PriceListService priceListService,
          ClientService clientService,
          CarService carService,
          OrderRepository orderRepository
  ) {
    this.employeeService = employeeService;
    this.priceListService = priceListService;
    this.clientService = clientService;
    this.carService = carService;
    this.orderRepository = orderRepository;
  }

  public OrderInfoDto getNewOrderInfo() {
    return new OrderInfoDto(
            employeeService.getAll(),
            priceListService.getAll()
    );
  }

  @Transactional(rollbackFor = { Exception.class })
  public Order create(OrderRequestDto order)
      throws ClientNotFoundException, EmployeeNotFoundException, PriceListNotFoundException {
    Client client = this.createClient(order.getClient());
    Car car = this.createCar(order.getCar(), client);
    Employee employee = this.employeeService.getById(order.getEmployeeId());

    PriceList priceList = this.priceListService.getById(order.getPriceListId());
    float price = order.getPrice();

    Order createdOrder = new Order(client, car, employee, priceList, price);

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

  public List<ShortOrder> getOrderList() {
    return this.orderRepository
      .findAll()
      .stream()
      .map(order -> OrderMapper.convertToShort(order))
      .collect(Collectors.toList());
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
