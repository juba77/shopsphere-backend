package com.shop.sphere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.sphere.api.model.BuyerDTO;
import com.shop.sphere.api.model.IdBuyer;
import com.shop.sphere.dao.BuyerRepository;
import com.shop.sphere.mappers.BuyerMapper;
import com.shop.sphere.models.Buyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestBuyerController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuyerRepository buyerRepository;

    private BuyerMapper buyerMapper = Mappers.getMapper(BuyerMapper.class);

    private Buyer testBuyer;
    private BuyerDTO testBuyerDTO;

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {
        testBuyer = new Buyer();
        testBuyer.setId(1L);
        testBuyer.setFirstName("Achraf");
        testBuyer.setLastName("ZERHOUNI");
        testBuyer.setEmail("achraf.zerhouni@shopsphere.com");
        testBuyer.setPassword("achrafPassword");
        testBuyer.setAddress("123 Boulevard Achraf");

        testBuyerDTO = buyerMapper.buyerToBuyerDto(testBuyer);
    }

    @Test
    public void testCreateBuyer() throws Exception {
        when(buyerRepository.save(any(Buyer.class))).thenReturn(testBuyer);

        mockMvc.perform(post("/api/buyers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBuyerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idBuyer").value(testBuyer.getId()));
    }

    @Test
    public void testGetBuyer() throws Exception {
        when(buyerRepository.findById(any(Long.class))).thenReturn(Optional.of(testBuyer));

        mockMvc.perform(get("/api/buyers/{id}", testBuyer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(testBuyer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testBuyer.getLastName()))
                .andExpect(jsonPath("$.email").value(testBuyer.getEmail()));
    }

    @Test
    public void testGetBuyers() throws Exception {
        when(buyerRepository.findAll()).thenReturn(Arrays.asList(testBuyer));

        mockMvc.perform(get("/api/buyers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(testBuyer.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(testBuyer.getLastName()))
                .andExpect(jsonPath("$[0].email").value(testBuyer.getEmail()));
    }

    @Test
    public void testUpdateBuyer() throws Exception {
        when(buyerRepository.findById(any(Long.class))).thenReturn(Optional.of(testBuyer));
        when(buyerRepository.save(any(Buyer.class))).thenReturn(testBuyer);

        mockMvc.perform(put("/api/buyers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBuyerDTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateBuyerNotFound() throws Exception {
        when(buyerRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/buyers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBuyerDTO)))
                .andExpect(status().isBadRequest());
    }


}
