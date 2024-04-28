package com.shop.sphere.dao;

import com.shop.sphere.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestProductRepository {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setPhoto("https://myphoto.png");
        product.setTitle("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(10.0);
        product.setQuantity(10);
        entityManager.persist(product);
        entityManager.flush();
    }

    @AfterEach
    public void tearDown() {
        entityManager.clear();
    }

    @Test
    public void testSaveProduct() {
        Product newProduct = new Product();
        newProduct.setPhoto("https://newphoto.png");
        newProduct.setTitle("New Product");
        newProduct.setDescription("This is a new product");
        newProduct.setPrice(15.0);
        newProduct.setQuantity(5);
        Product savedProduct = productRepository.save(newProduct);
        
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getTitle()).isEqualTo(newProduct.getTitle());
    }

    @Test
    public void whenFindById_thenReturnProduct() {
        Product found = productRepository.findById(product.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo(product.getTitle());
    }

    @Test
    public void whenFindByInvalidId_thenReturnNull() {
        Optional<Product> found = productRepository.findById(-1L);
        assertThat(found).isNotPresent();
    }

    @Test
    public void whenUpdate_thenReturnUpdatedProduct() {
        product.setTitle("Updated Product");
        Product updatedProduct = productRepository.save(product);
        assertThat(updatedProduct.getTitle()).isEqualTo("Updated Product");
    }

    @Test
    public void whenDelete_thenRemoveProduct() {
        productRepository.deleteById(product.getId());
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertThat(deletedProduct).isNotPresent();
    }

}
