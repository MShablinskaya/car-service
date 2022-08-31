package com.innowise.sharing.controller.impl;

import com.innowise.sharing.controller.OrderRest;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class OrderRestImpl implements OrderRest {
    private final OrderService orderService;

    @Override
    public void postNewOrder(@Valid OrderDto dto) {
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
