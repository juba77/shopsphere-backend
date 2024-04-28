package com.shop.sphere.mappers;

import com.shop.sphere.api.model.UserConnectedDTO;
import com.shop.sphere.models.Admin;
import com.shop.sphere.models.Buyer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserConnectedMapperTest {

    UserConnectedMapper userConnectedMapper = Mappers.getMapper(UserConnectedMapper.class);

    @Test
    void adminToUserConnectedDto(){
        Admin admin = new Admin();
        admin.setFirstName("Juba");
        admin.setLastName("Ladmin");
        admin.setEmail("juba.ladmin@shopsphere.com");
        admin.setPassword("AdminMdp10");

        UserConnectedDTO userConnectedDTO = userConnectedMapper.adminToUserConnectedDto(admin);

        assertEquals(admin.getId(),userConnectedDTO.getId());
        assertEquals(admin.getFirstName(),userConnectedDTO.getFirstName());
        assertEquals(admin.getLastName(),userConnectedDTO.getLastName());
        assertEquals(admin.getEmail(),userConnectedDTO.getEmail());
    }

    @Test
    void buyerToUserConnectedDto(){
        Buyer buyer = new Buyer();
        buyer.setFirstName("Juba");
        buyer.setLastName("Buy");
        buyer.setEmail("juba.buy@shopsphere.com");
        buyer.setPassword("AdminMdp10");
        buyer.setAddress("14 rue des patates");

        UserConnectedDTO userConnectedDTO = userConnectedMapper.buyerToUserConnectedDto(buyer);

        assertEquals(buyer.getId(),userConnectedDTO.getId());
        assertEquals(buyer.getFirstName(),userConnectedDTO.getFirstName());
        assertEquals(buyer.getLastName(),userConnectedDTO.getLastName());
        assertEquals(buyer.getEmail(),userConnectedDTO.getEmail());
        assertEquals(buyer.getAddress(),userConnectedDTO.getAddress());
    }

}
