package com.shop.sphere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shop.sphere.api.model.OrderDTO;
import com.shop.sphere.dao.OrderRepository;
import com.shop.sphere.mappers.OrderMapper;
import com.shop.sphere.models.Buyer;
import com.shop.sphere.models.Order;
import com.shop.sphere.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TestOrderController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    private Order testOrder;
    private OrderDTO testOrderDTO;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setDate(LocalDate.now());
        testOrder.setIsFinalized(true);

        List<Product> articles = new ArrayList<>();

        Product product = new Product();
        product.setDescription("description test");
        product.setId(1L);
        product.setPhoto("https://fefef.fzfzfz.com");
        product.setPrice(22.5);
        product.setTitle("Titre");
        product.setQuantity(85);

        Product product2 = new Product();
        product2.setDescription("description test for the second product");
        product2.setId(1L);
        product2.setPhoto("https://fefefezded.fzfzfz.com");
        product2.setPrice(2.5);
        product2.setTitle("second produit");
        product2.setQuantity(85);

        articles.add(product);
        articles.add(product2);
        testOrder.setArticles(articles);

        Buyer client = new Buyer();
        client.setId(1L);
        client.setFirstName("ClientFirstName");
        client.setLastName("ClientLastName");
        client.setEmail("client.email@example.com");
        client.setPassword("securePassword");
        client.setAddress("123 Client Address Street");
        testOrder.setClient(client);

        testOrderDTO = orderMapper.orderToOrderDto(testOrder);

        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testOrder));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(testOrder);
        Mockito.when(orderRepository.findAll()).thenReturn(Collections.singletonList(testOrder));

    }

    @Test
    void createOrderTest() throws Exception {
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(testOrder);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testOrderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idOrder").value(testOrder.getId()));
    }
}
