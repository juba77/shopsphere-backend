package com.shop.sphere.dao;

import com.shop.sphere.models.Buyer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestBuyerRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BuyerRepository buyerRepository;

    @Test
    public void testBuyerCreate() {
        Buyer buyer = new Buyer();
        buyer.setFirstName("Achraf");
        buyer.setLastName("ZERHOUNI");
        buyer.setEmail("achraf.zerhouni@shopsphere.com");
        buyer.setPassword("achrafPassword");
        buyer.setAddress("123 Boulevard Achraf");

        Buyer savedBuyer = buyerRepository.save(buyer);
        entityManager.flush();  // Ensure changes are pushed to the test database

        Buyer foundBuyer = entityManager.find(Buyer.class, savedBuyer.getId());

        assertThat(foundBuyer).isNotNull();
        assertThat(foundBuyer.getFirstName()).isEqualTo(buyer.getFirstName());
        assertThat(foundBuyer.getLastName()).isEqualTo(buyer.getLastName());
        assertThat(foundBuyer.getEmail()).isEqualTo(buyer.getEmail());
        assertThat(foundBuyer.getPassword()).isEqualTo(buyer.getPassword());
        assertThat(foundBuyer.getAddress()).isEqualTo(buyer.getAddress());
    }

    @Test
    public void testBuyerUpdate() {
        // 1. Create and save an initial buyer (Juba OUARAB)
        Buyer buyer = new Buyer();
        buyer.setFirstName("Juba");
        buyer.setLastName("OUARAB");
        buyer.setEmail("juba.ouarab@shopsphere.com");
        buyer.setPassword("jubaPassword");
        buyer.setAddress("456 Avenue Juba");
        Buyer savedBuyer = buyerRepository.save(buyer);
        entityManager.flush();

        // 2. Update the saved buyer's attributes
        savedBuyer.setFirstName("Kpotivi");
        savedBuyer.setLastName("KPOTY");
        savedBuyer.setEmail("kpotivi.kpoty@shopsphere.com");
        savedBuyer.setPassword("kpotiviPassword");
        savedBuyer.setAddress("789 Street Kpotivi");
        Buyer updatedBuyer = buyerRepository.save(savedBuyer);
        entityManager.flush();

        // 3. Fetch the updated buyer from the database and verify the changes
        Buyer foundBuyer = entityManager.find(Buyer.class, updatedBuyer.getId());

        assertThat(foundBuyer).isNotNull();
        assertThat(foundBuyer.getFirstName()).isEqualTo("Kpotivi");
        assertThat(foundBuyer.getLastName()).isEqualTo("KPOTY");
        assertThat(foundBuyer.getEmail()).isEqualTo("kpotivi.kpoty@shopsphere.com");
        assertThat(foundBuyer.getPassword()).isEqualTo("kpotiviPassword");
        assertThat(foundBuyer.getAddress()).isEqualTo("789 Street Kpotivi");
    }

    @Test
    public void testBuyerDelete() {
        // Create two buyers
        Buyer buyer1 = new Buyer();
        Buyer buyer2 = new Buyer();

        buyer1.setFirstName("Juba");
        buyer1.setLastName("OUARAB");
        buyer1.setEmail("juba.ouarab@shopsphere.com");
        buyer1.setPassword("jubaPassword");
        buyer1.setAddress("123 Street A");

        buyer2.setFirstName("Kpotivi");
        buyer2.setLastName("KPOTY");
        buyer2.setEmail("kpotivi.kpoty@shopsphere.com");
        buyer2.setPassword("kpotiviPassword");
        buyer2.setAddress("456 Street B");

        // Persist both buyers
        entityManager.persist(buyer1);
        entityManager.persist(buyer2);
        entityManager.flush();

        // Delete buyer1
        buyerRepository.delete(buyer1);
        entityManager.flush();

        // Retrieve buyer1 and buyer2 from the database
        Buyer foundBuyer1 = entityManager.find(Buyer.class, buyer1.getId());
        Buyer foundBuyer2 = entityManager.find(Buyer.class, buyer2.getId());

        // Assert that buyer1 is deleted (should be null) and buyer2 is still there
        assertThat(foundBuyer1).isNull();
        assertThat(foundBuyer2).isNotNull();
        assertThat(foundBuyer2.getFirstName()).isEqualTo(buyer2.getFirstName());
        assertThat(foundBuyer2.getLastName()).isEqualTo(buyer2.getLastName());
    }

}
