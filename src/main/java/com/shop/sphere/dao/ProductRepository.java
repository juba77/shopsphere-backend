package com.shop.sphere.dao;

import com.shop.sphere.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Product} entities.
 * <p>
 * Provides CRUD operations on Product entities.
 * </p>
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
