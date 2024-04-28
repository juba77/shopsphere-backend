package com.shop.sphere.dao;

import com.shop.sphere.models.Admin;
import com.shop.sphere.dao.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestAdminRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
        admin.setFirstName("Achraf");
        admin.setLastName("ZERHOUNI");
        admin.setEmail("Achraf.zerhouni@shopsphere.com");
        admin.setPassword("achrafPassword");
        entityManager.persist(admin);
        entityManager.flush();
    }

    @Test
    public void testAdminCreate() {
        Admin newAdmin = new Admin();
        newAdmin.setFirstName("Laura");
        newAdmin.setLastName("COUSIN");
        newAdmin.setEmail("laura.cousin@shopsphere.com");
        newAdmin.setPassword("lauraPassword");

        Admin savedAdmin = adminRepository.save(newAdmin);
        entityManager.flush();

        Admin foundAdmin = entityManager.find(Admin.class, savedAdmin.getId());

        assertThat(foundAdmin).isNotNull();
        assertThat(foundAdmin.getFirstName()).isEqualTo(newAdmin.getFirstName());
        assertThat(foundAdmin.getEmail()).isEqualTo(newAdmin.getEmail());
    }


    @Test
    public void testAdminRead() {
        Optional<Admin> foundAdmin = adminRepository.findById(admin.getId());
        assertThat(foundAdmin).isPresent();
        assertThat(foundAdmin.get().getEmail()).isEqualTo(admin.getEmail());
    }

    @Test
    public void testAdminUpdate() {
        Optional<Admin> foundAdmin = adminRepository.findById(admin.getId());
        assertThat(foundAdmin).isPresent();

        foundAdmin.get().setEmail("new.email@shopsphere.com");
        adminRepository.save(foundAdmin.get());

        Optional<Admin> updatedAdmin = adminRepository.findById(admin.getId());
        assertThat(updatedAdmin).isPresent();
        assertThat(updatedAdmin.get().getEmail()).isEqualTo("new.email@shopsphere.com");
    }

    @Test
    public void testAdminDelete() {
        adminRepository.delete(admin);
        entityManager.flush();

        Optional<Admin> deletedAdmin = adminRepository.findById(admin.getId());
        assertThat(deletedAdmin).isNotPresent();
    }


}

