package com.example.RestApiApplication.Mappers;

import com.example.RestApiApplication.Dtos.Responses.OrderResponseDto;
import com.example.RestApiApplication.Dtos.Responses.ShortOrder;
import com.example.RestApiApplication.Entities.Order;

public class OrderMapper {
  public static OrderResponseDto convertEntityToDto(Order order) {
    return new OrderResponseDto(
      order.getId(),
      order.getClient().getId(),
      order.getEmployee().getId(),
      order.getPriceList().getId(),
      order.getPrice(),
      order.getCreatedAt()
    );
  }

  public static ShortOrder convertToShort(Order order) {
    return new ShortOrder(
            order.getId(),
            order.getClient().getName(),
            order.getCreatedAt()
    );
  }
}
