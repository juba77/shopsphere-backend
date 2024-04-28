package com.shop.sphere.mappers;

import com.shop.sphere.api.model.OrderDTO;
import com.shop.sphere.models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderMapperTest {

    private OrderMapper orderMapper;

    @BeforeEach
    void setup() {
        orderMapper = Mappers.getMapper(OrderMapper.class);
    }

    @Test
    void shouldMapOrderToOrderDto() {
        LocalDate date = LocalDate.of(2023, 4, 1);
        Order order = new Order();
        order.setDate(date);

        OrderDTO orderDTO = orderMapper.orderToOrderDto(order);

        assertThat(orderDTO).isNotNull();
        assertThat(orderDTO.getDate()).isEqualTo("01/04/2023");
    }

    @Test
    void shouldMapOrderDtoToOrder() {
        String dateString = "01/04/2023";
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDate(dateString);

        Order order = orderMapper.orderDtoToOrder(orderDTO);

        assertThat(order).isNotNull();
        assertThat(order.getDate()).isEqualTo(LocalDate.of(2023, 4, 1));
    }
}
