package com.innowise.sharing.service;

import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.enums.Action;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface OrderService {
    void createNewCarOrder(@Valid OrderDto orderDto);

    void updateStateOfCarOrder(Long orderId, Action action);

    OrderDto getOrderById(Long id);

    List<OrderDto> getMyOrders(@Valid String email);
}
