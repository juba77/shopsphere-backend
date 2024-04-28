package com.shop.sphere.service;

import com.shop.sphere.dao.AdminRepository;
import com.shop.sphere.dao.BuyerRepository;
import com.shop.sphere.models.Admin;
import com.shop.sphere.models.Buyer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private BuyerService buyerService;

    private Admin admin;
    private Buyer buyer;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        admin.setFirstName("Juba");
        admin.setLastName("Ladmin");
        admin.setEmail("juba.ladmin@shopsphere.com");
        admin.setPassword("AdminMdp10");
        adminRepository.save(admin);

        buyer = new Buyer();
        buyer.setFirstName("Juba");
        buyer.setLastName("Lebuyer");
        buyer.setEmail("juba.lebuyer@gmail.com");
        buyer.setPassword("BuyerMdp10");
        buyer.setAddress("10 rue des fleurs et des cornichons");
        buyerService.saveBuyer(buyer);
    }

    @AfterEach
    void tearDown(){
        adminRepository.delete(admin);
        buyerRepository.delete(buyer);
    }

    @Test
    public void testFindAdminPositive(){
        Admin admin1 = loginService.findAdminByEmailAndPassword("juba.ladmin@shopsphere.com","AdminMdp10");
        assertEquals(admin1.getId(),admin.getId());
        assertEquals(admin1.getEmail(),admin.getEmail());
        assertEquals(admin1.getFirstName(),admin.getFirstName());
    }

    @Test
    public void testFindAdminNegative(){
        Buyer buyer1 = loginService.findBuyerByEmailAndPassword("juba.lebuyer@gmail.com","BuyerMdp10");
        assertEquals(buyer1.getId(),buyer.getId());
        assertEquals(buyer1.getFirstName(),buyer.getFirstName());
        assertEquals(buyer1.getAddress(),buyer.getAddress());
    }

    @Test
    public void testFindBuyerPositive(){
        assertNull(loginService.findBuyerByEmailAndPassword("emailIncorrect@gmail.com", "BuyerMdp10"));
        assertNull(loginService.findBuyerByEmailAndPassword("juba.lebuyer@gmail.com", "MotDePasseIncorrect"));
        assertNull(loginService.findBuyerByEmailAndPassword("emailIncorrect@gmail.com", "MotDePasseIncorrect"));
    }

    @Test
    public void testFindBuyerNegative(){
        assertNull(loginService.findBuyerByEmailAndPassword("emailIncorrect@gmail.com", "BuyerMdp10"));
        assertNull(loginService.findBuyerByEmailAndPassword("juba.lebuyer@gmail.com", "MotDePasseIncorrect"));
        assertNull(loginService.findBuyerByEmailAndPassword("emailIncorrect@gmail.com", "MotDePasseIncorrect"));
    }

    @Test
    public void loginAdminPositive(){
        assertEquals(loginService.login("juba.ladmin@shopsphere.com","AdminMdp10").getStatusCode().value(),200);
    }

    @Test
    public void loginAdminNegative(){
        assertEquals(loginService.login("juba.ladmin@shopsphere.com","incorrect").getStatusCode().value(),400);
    }

    @Test
    public void loginBuyerPositive(){
        assertEquals(loginService.login("juba.lebuyer@gmail.com","BuyerMdp10").getStatusCode().value(),200);
    }

    @Test
    public void loginBuyerNegative(){
        assertEquals(loginService.login("juba.lebuyer@gmail.com","incorrect").getStatusCode().value(),400);
    }
}