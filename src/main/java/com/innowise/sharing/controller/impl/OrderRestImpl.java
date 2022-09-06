package com.innowise.sharing.controller.impl;

import com.innowise.sharing.controller.OrderRest;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderRestImpl implements OrderRest {
    private final OrderService orderService;

    @Override
    public void postNewOrder(OrderDto dto) {
        orderService.createNewCarOrder(dto);
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Override
    public ResponseEntity<List<OrderDto>> getMyOrders(String email) {
        return ResponseEntity.ok(orderService.getMyOrders(email));
    }

    @Override
    public void updateStateCarOrder(Long id, String action) {
        orderService.updateStateOfCarOrder(id, action);
    }
}
