package com.shop.sphere.mappers;

import com.shop.sphere.api.model.OrderDTO;
import com.shop.sphere.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "date", expression = "java(parseDateToString(order.getDate()))")
    OrderDTO orderToOrderDto(Order order);

    @Mapping(target = "date", expression = "java(parseStringToDate(orderDTO.getDate()))")
    Order orderDtoToOrder(OrderDTO orderDTO);

    default LocalDate parseStringToDate(String string) {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    default String parseDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
