package com.orderservice.service;

import com.orderservice.domain.Order;
import com.orderservice.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<Order> getOrderByUserId(String userId);
}
