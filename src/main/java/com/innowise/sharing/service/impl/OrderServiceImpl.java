package com.innowise.sharing.service.impl;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.Order;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.enums.Action;
import com.innowise.sharing.enums.State;
import com.innowise.sharing.kafka.producer.MessageProducer;
import com.innowise.sharing.mapper.OrderMapper;
import com.innowise.sharing.repository.OrderRepository;
import com.innowise.sharing.service.CarService;
import com.innowise.sharing.service.OrderService;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final CarService carService;
    @Autowired
    @Qualifier("orderMessageProducer")
    private MessageProducer<Order> messageProducer;


    @Override
    public void createNewCarOrder(OrderDto orderDto) {
        Timestamp currentDate = Timestamp.from(Instant.now());
        Long carId = orderDto.getCar().getId();
        CarDto carDto = carService.findCarDtoById(carId);
        if (Boolean.TRUE.equals(carDto.getAvailability())) {
            Order order = saveOrder(orderDto);
            order.setBookingDate(currentDate);
            order.setState(State.RESERVED);
            orderRepository.save(order);

            messageProducer.send(order);
        }
    }

    @Override
    public void updateStateOfCarOrder(Long orderId, String action) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        String incomingState = Action.valueOf(action.toUpperCase()).getIncomingState();
        String currentState = order.getState().toString();
        Long carId = order.getCar().getId();
        List<String> availableActions = availableActions(orderId);
        if (!currentState.equals(incomingState) && availableActions.contains(action.toUpperCase())) {
            State state = State.valueOf(incomingState);
            order.setState(state);
            revertCarIfOrderDone(carId, orderId);
            orderRepository.save(order);
        }
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::setCarAndCustomer)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<OrderDto> getMyOrders(String email) {
        return orderRepository.selectMyOrders(email)
                .stream()
                .map(this::setCarAndCustomer)
                .collect(Collectors.toList());
    }

    private OrderDto setCarAndCustomer(Order order) {
        Long carId = order.getCar().getId();
        String email = order.getCustomer().getEmail();
        CarDto carDto = carService.findCarDtoById(carId);
        UserDto userDto = userService.getUserDtoByEmail(email);
        OrderDto dto = orderMapper.orderToOrderDto(order);
        List<String> actions = availableActions(order.getId());
        dto.setCustomer(userDto);
        dto.setCar(carDto);
        dto.setActions(actions);

        return dto;
    }

    private Order saveOrder(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        Long carId = orderDto.getCar().getId();
        String email = orderDto.getCustomer().getEmail();
        Car car = carService.getCarEntityById(carId);
        User customer = userService.getUserByEmail(email);
        order.setCar(car);
        order.setCustomer(customer);
        carService.changeAvailabilityStatus(carId);

        return orderRepository.save(order);
    }

    private List<String> availableActions(Long id) {
        OrderDto orderDto = orderRepository.findById(id)
                .map(orderMapper::orderToOrderDto)
                .orElseThrow(EntityNotFoundException::new);
        List<String> actions = new ArrayList<>();
        if (orderDto.getState().equals(State.RESERVED.toString())) {
            actions.add(Action.CANCEL.toString());
            actions.add(Action.CONFIRM.toString());
        } else if (orderDto.getState().equals(State.IN_USE.toString())) {
            actions.add(Action.RETURN.toString());
        }

        return actions;
    }

    private void revertCarIfOrderDone(Long carId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        String state = order.getState().toString();
        Timestamp currentDate = Timestamp.from(Instant.now());
        if (state.equals(State.RETURNED.toString()) || state.equals(State.CANCELLED.toString())) {
            carService.changeAvailabilityStatus(carId);
            order.setReturnDate(currentDate);
            orderRepository.save(order);
        }
    }
}
