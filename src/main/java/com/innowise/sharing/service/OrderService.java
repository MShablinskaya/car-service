package com.innowise.sharing.service;

import com.innowise.sharing.dto.OrderDto;

import java.util.List;

public interface OrderService {
    boolean createNewCarOrder(OrderDto orderDto);

    boolean updateStateOfCarOrder(Long orderId, String action);

    OrderDto getOrderById(Long id);

    List<OrderDto> getMyOrders(String email);
}
