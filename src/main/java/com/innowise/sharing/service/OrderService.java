package com.innowise.sharing.service;

import com.innowise.sharing.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void createNewCarOrder(OrderDto orderDto);

    void updateStateOfCarOrder(Long orderId, String action);

    OrderDto getOrderById(Long id);

    List<OrderDto> getMyOrders(String email);
}
