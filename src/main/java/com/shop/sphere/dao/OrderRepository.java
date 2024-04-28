package com.shop.sphere.dao;

import com.shop.sphere.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Order} entities.
 * <p>
 * Provides CRUD operations on Order entities.
 * </p>
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.client.id = :buyerId")
    List<Order> findOrdersByBuyerId(Long buyerId);
}