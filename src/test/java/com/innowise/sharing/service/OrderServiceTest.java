package com.innowise.sharing.service;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Order;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.exception.CarIsNotAvailableException;
import com.innowise.sharing.exception.OrderEntityNotFoundException;
import com.innowise.sharing.mapper.OrderMapper;
import com.innowise.sharing.repository.OrderRepository;
import com.innowise.sharing.service.impl.OrderServiceImpl;
import com.innowise.sharing.util.CarTestUtil;
import com.innowise.sharing.util.OrderTestUtil;
import com.innowise.sharing.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private CarService carService;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createNewCarOrder_When_Successful() {
        OrderDto orderForSaving = OrderTestUtil.createOrderDto();
        Order expectedOrder = OrderTestUtil.createOrder();
        User customer = UserTestUtil.createUser();

        when(carService.isAvailable(CarTestUtil.ID)).thenReturn(true);
        when(userService.getUserByEmail(UserTestUtil.EMAIL)).thenReturn(customer);
        when(orderMapper.orderDtoToOrder(orderForSaving)).thenReturn(expectedOrder);
        when(orderRepository.save(expectedOrder)).thenReturn(expectedOrder);

        orderService.createNewCarOrder(orderForSaving);

        verify(orderMapper).orderDtoToOrder(orderForSaving);
        verify(orderRepository, times(2)).save(expectedOrder);
    }

    @Test
    void updateStateOfCarOrder_When_Successful() {
        Order expectedOrder = OrderTestUtil.createOrder();

        when(orderRepository.findById(OrderTestUtil.ID)).thenReturn(Optional.ofNullable(expectedOrder));
        assert expectedOrder != null;
        when(orderRepository.save(expectedOrder)).thenReturn(expectedOrder);

        orderService.updateStateOfCarOrder(OrderTestUtil.ID, OrderTestUtil.ACTION);

        verify(orderRepository).save(expectedOrder);
    }

    @Test
    void getOrderById_When_Successful() {
        OrderDto expectedDto = OrderTestUtil.createOrderDto();
        Order expectedOrder = OrderTestUtil.createOrder();
        CarDto carDto = CarTestUtil.createCarDto();
        UserDto customerDto = UserTestUtil.createUserDto();

        when(carService.findCarDtoById(CarTestUtil.ID)).thenReturn(carDto);
        when(userService.getUserDtoByEmail(UserTestUtil.EMAIL)).thenReturn(customerDto);
        when(orderMapper.orderToOrderDto(expectedOrder)).thenReturn(expectedDto);
        when(orderRepository.findById(OrderTestUtil.ID)).thenReturn(Optional.ofNullable(expectedOrder));

        OrderDto currentOrder = orderService.getOrderById(OrderTestUtil.ID);

        assertThat(currentOrder).isEqualTo(expectedDto);

    }

    @Test
    void getMyOrders_When_Successful() {
        List<OrderDto> expectedDtoList = new ArrayList<>();
        List<Order> expectedOrderList = new ArrayList<>();
        OrderDto expectedDto = OrderTestUtil.createOrderDto();
        Order expectedOrder = OrderTestUtil.createOrder();
        CarDto carDto = CarTestUtil.createCarDto();
        UserDto customerDto = UserTestUtil.createUserDto();
        expectedDtoList.add(expectedDto);
        expectedOrderList.add(expectedOrder);

        when(carService.findCarDtoById(CarTestUtil.ID)).thenReturn(carDto);
        when(userService.getUserDtoByEmail(UserTestUtil.EMAIL)).thenReturn(customerDto);
        when(orderMapper.orderToOrderDto(expectedOrder)).thenReturn(expectedDto);
        when(orderRepository.selectMyOrders(UserTestUtil.EMAIL)).thenReturn(expectedOrderList);
        when(orderRepository.findById(OrderTestUtil.ID)).thenReturn(Optional.ofNullable(expectedOrder));

        List<OrderDto> currentOrderDtoList = orderService.getMyOrders(UserTestUtil.EMAIL);

        assertThat(currentOrderDtoList).isEqualTo(expectedDtoList);
    }

    @Test
    void createNewCarOrder_When_Failed_Because_Car_Is_Not_Available() {
        Order expectedOrder = OrderTestUtil.createOrder();
        OrderDto expectedDto = OrderTestUtil.createOrderDto();

        when(carService.isAvailable(CarTestUtil.ID)).thenReturn(false);

        assertThatThrownBy(() -> orderService.createNewCarOrder(expectedDto))
                .isInstanceOf(CarIsNotAvailableException.class)
                .hasMessage(String.format("Car with ID:%s is not available right now.", CarTestUtil.ID));

    }

    @Test
    void getOrderById_When_Entity_Not_Found() {
        when(orderRepository.findById(OrderTestUtil.ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getOrderById(OrderTestUtil.ID))
                .isInstanceOf(OrderEntityNotFoundException.class)
                .hasMessage(String.format("Order with ID: %s is not found", OrderTestUtil.ID));
    }
}