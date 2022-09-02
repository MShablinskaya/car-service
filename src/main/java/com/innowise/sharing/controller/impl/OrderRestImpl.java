package com.innowise.sharing.controller.impl;

import com.innowise.sharing.controller.OrderRest;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class OrderRestImpl implements OrderRest {
    private final OrderService orderService;

    @Override
    public ResponseEntity<HttpStatus> postNewOrder(OrderDto dto) {

        return orderService.createNewCarOrder(dto) ?
                new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<HttpStatus> updateStateCarOrder(Long id, String action) {
        return orderService.updateStateOfCarOrder(id, action) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
