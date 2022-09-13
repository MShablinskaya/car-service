package com.innowise.sharing.service.impl;

import com.innowise.sharing.dto.CarDto;
import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.dto.UserDto;
import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.Order;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.enums.Action;
import com.innowise.sharing.enums.State;
import com.innowise.sharing.exception.CarIsNotAvailableException;
import com.innowise.sharing.exception.OrderEntityNotFoundException;
import com.innowise.sharing.mapper.OrderMapper;
import com.innowise.sharing.repository.OrderRepository;
import com.innowise.sharing.service.CarService;
import com.innowise.sharing.service.OrderService;
import com.innowise.sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final CarService carService;


    @Override
    @Transactional
    public void createNewCarOrder(OrderDto orderDto) {
        Instant currentDate = Instant.now();
        Long carId = orderDto.getCar().getId();
        if (carService.isAvailable(carId)) {
            Order order = saveOrder(orderDto);
            order.setBookingDate(currentDate);
            order.setState(State.RESERVED);
            orderRepository.save(order);
        } else {
            throw new CarIsNotAvailableException(carId);
        }
    }

    @Override
    @Transactional
    public void updateStateOfCarOrder(Long orderId, Action action) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderEntityNotFoundException(orderId));
        State incomingState = State.valueOf(action.getIncomingState());
        State currentState = order.getState();
        Long carId = order.getCar().getId();
        List<Action> availableActions = availableActions(orderId);
        if (!currentState.equals(incomingState) && availableActions.contains(action)) {
            order.setState(incomingState);
            revertCarIfOrderDone(carId, orderId);
            orderRepository.save(order);
        }
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::setCarAndCustomer)
                .orElseThrow(() -> new OrderEntityNotFoundException(orderId));
    }

    @Override
    public List<OrderDto> getMyOrders(String email) {
        return orderRepository.selectMyOrders(email)
                .stream()
                .map(this::setCarAndCustomer)
                .toList();
    }

    private OrderDto setCarAndCustomer(Order order) {
        Long carId = order.getCar().getId();
        String email = order.getCustomer().getEmail();
        CarDto carDto = carService.findCarDtoById(carId);
        UserDto userDto = userService.getUserDtoByEmail(email);
        OrderDto dto = orderMapper.orderToOrderDto(order);
        List<Action> actions = availableActions(order.getId());
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

    private List<Action> availableActions(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        List<Action> actions = new ArrayList<>();
        State currentState = order.getState();
        switch (currentState) {
            case RESERVED -> {
                actions.add(Action.CONFIRM);
                actions.add(Action.CANCEL);
            }
            case IN_USE -> actions.add(Action.RETURN);
            default -> {
                return actions;
            }
        }

        return actions;
    }

    private void revertCarIfOrderDone(Long carId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        String state = order.getState().toString();
        Instant currentDate = Instant.now();
        if (state.equals(State.RETURNED.toString()) || state.equals(State.CANCELLED.toString())) {
            carService.changeAvailabilityStatus(carId);
            order.setReturnDate(currentDate);
            orderRepository.save(order);
        }
    }
}
