package com.shop.sphere.controllers;

import com.shop.sphere.dao.ProductRepository;
import com.shop.sphere.mappers.ProductMapper;
import com.shop.sphere.models.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getProduct() throws Exception {
        Product product = new Product();
        product.setDescription("description test");
        product.setId(1L);
        product.setPhoto("https://fefef.fzfzfz.com");
        product.setPrice(22.5);
        product.setTitle("Titre");
        product.setQuantity(85);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("description test"))
                .andExpect(jsonPath("$.photo").value("https://fefef.fzfzfz.com"))
                .andExpect(jsonPath("$.price").value(22.5))
                .andExpect(jsonPath("$.title").value("Titre"))
                .andExpect(jsonPath("$.quantity").value(85));
    }

    @Test
    void getProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("Produit 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Produit 2");

        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].description").value("Produit 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].description").value("Produit 2"));
    }

    @Test
    void updateProduct() throws Exception {
        Product product = new Product();
        product.setDescription("description test");
        product.setPhoto("https://fefef.fzfzfz.png");
        product.setPrice(22.5);
        product.setTitle("Titre");
        product.setQuantity(85);

        productRepository.save(product);
        product.setTitle("new title");
        when(productRepository.save(product)).thenReturn(product);

        mockMvc.perform(put("/api/products")
                        .content(objectMapper.writeValueAsString(productMapper.productToProductDto(product)))
                        .contentType("application/json"))
                .andExpect(status().isNoContent());
    }

}