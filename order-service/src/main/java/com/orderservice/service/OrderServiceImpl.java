package com.orderservice.service;

import com.orderservice.domain.Order;
import com.orderservice.dto.OrderDto;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

        Order order = modelMapper.map(orderDto, Order.class);
        orderRepository.save(order);

        return orderDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);

        return orderDto;
    }

    @Override
    public Iterable<Order> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

}
