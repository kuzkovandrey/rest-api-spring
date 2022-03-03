package com.example.RestApiApplication.Controllers;

import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Entities.Order;
import com.example.RestApiApplication.Models.ApiOrderModel;
import com.example.RestApiApplication.Services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ApiController.ORDERS)
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order create(@RequestBody ApiOrderModel order) {
    try {
      return this.orderService.create(order);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @GetMapping(ApiController.ID_PATH)
  @ResponseStatus(HttpStatus.OK)
  public Order getById(@PathVariable("id") Long id) {}
}
