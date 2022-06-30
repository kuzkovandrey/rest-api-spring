package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.PathVariables;
import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Dtos.Requests.OrderRequestDto;
import com.example.RestApiApplication.Dtos.Responses.OrderInfoDto;
import com.example.RestApiApplication.Dtos.Responses.OrderResponseDto;
import com.example.RestApiApplication.Dtos.Responses.ShortOrder;
import com.example.RestApiApplication.Entities.Order;
import com.example.RestApiApplication.Exceptions.OrderNotFoundException;
import com.example.RestApiApplication.Mappers.OrderMapper;
import com.example.RestApiApplication.Services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ApiController.ORDERS)
public class OrderController {
  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResponseDto create(@RequestBody OrderRequestDto order) {
    try {
      Order createdOrder = this.orderService.create(order);
      return OrderMapper.convertEntityToDto(createdOrder);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Order> getAll() {
    return this.orderService.getAll();
  }

  @GetMapping(ApiController.INFO)
  @ResponseStatus(HttpStatus.OK)
  public OrderInfoDto getNewOrderInfo() {
    return this.orderService.getNewOrderInfo();
  }

  @GetMapping(ApiController.LIST)
  @ResponseStatus(HttpStatus.OK)
  public List<ShortOrder> getOrderList() {
    return this.orderService.getOrderList();
  }

  @GetMapping(ApiController.ID_PATH)
  @ResponseStatus(HttpStatus.OK)
  public Order getById(@PathVariable(PathVariables.ID) Long id) {
    try {

      return this.orderService.getById(id);
      
    } catch (OrderNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(ApiController.ID_PATH)
  public ResponseEntity<Long> delete(@PathVariable(PathVariables.ID) Long id) {
    this.orderService.delete(id);

    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }
}
