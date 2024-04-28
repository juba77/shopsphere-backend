package com.shop.sphere.mappers;

import com.shop.sphere.api.model.AdminDTO;
import com.shop.sphere.models.Admin;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdminMapperTest {

    @Test
    void adminToAdminDto(){

        Admin admin = new Admin();
        admin.setId(12L);
        admin.setEmail("aa@aa.aa");
        admin.setFirstName("aa");
        admin.setLastName("bb");
        admin.setPassword("azer");

        AdminMapper adminMapper = Mappers.getMapper(AdminMapper.class);
        AdminDTO adminDTO = adminMapper.adminToAdminDto(admin);

        assertEquals(adminDTO.getId(),admin.getId());
        assertEquals(adminDTO.getEmail(),admin.getEmail());
        assertEquals(adminDTO.getFirstName(),admin.getFirstName());
        assertEquals(adminDTO.getLastName(),admin.getLastName());

    }

}
