package com.shop.sphere.controllers;

import com.shop.sphere.api.OrdersApi;
import com.shop.sphere.api.model.IdOrder;
import com.shop.sphere.api.model.OrderDTO;
import com.shop.sphere.dao.OrderRepository;
import com.shop.sphere.mappers.OrderMapper;
import com.shop.sphere.models.Order;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderRestController implements OrdersApi {

    @Autowired
    private OrderRepository orderRepository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public ResponseEntity<IdOrder> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.orderDtoToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);
        IdOrder idOrder = new IdOrder();
        idOrder.setIdOrder(savedOrder.getId());
        return new ResponseEntity<>(idOrder, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long buyerId) {
        List<Order> orders = orderRepository.findOrdersByBuyerId(buyerId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<OrderDTO> orderDTOS = orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }
}
