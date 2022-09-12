package com.innowise.sharing.mapper;

import com.innowise.sharing.dto.OrderDto;
import com.innowise.sharing.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderDtoToOrder(OrderDto dto);

    OrderDto orderToOrderDto(Order order);
}
