package com.shop.myshop.mapper;

import com.shop.myshop.dto.OrderDto;
import com.shop.myshop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);

    Order orderDtoToOrderModel(OrderDto orderDto);
    OrderDto orderModelToOrderDto(Order order);


}
