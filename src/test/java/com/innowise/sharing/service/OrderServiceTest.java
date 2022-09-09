package com.innowise.sharing.service;

import com.innowise.sharing.mapper.OrderMapper;
import com.innowise.sharing.repository.OrderRepository;
import com.innowise.sharing.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test

    void createNewCarOrder() {
    }

    @Test
    void updateStateOfCarOrder() {
    }

    @Test
    void getOrderById() {
    }

    @Test
    void getMyOrders() {
    }
}