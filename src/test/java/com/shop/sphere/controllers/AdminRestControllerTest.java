package com.shop.sphere.controllers;

import com.shop.sphere.dao.AdminRepository;
import com.shop.sphere.models.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminRepository adminRepository;

    @Test
    public void testGetAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setFirstName("A");
        admin.setLastName("B");
        admin.setEmail("aa@bb.cc");
        admin.setPassword("dffof9494948Adfdifn");

        when(adminRepository.findById(1L)).thenReturn(java.util.Optional.of(admin));

        mockMvc.perform(get("/api/admins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("A"))
                .andExpect(jsonPath("$.lastName").value("B"))
                .andExpect(jsonPath("$.email").value("aa@bb.cc"));
    }
}
