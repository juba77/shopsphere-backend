package com.shop.sphere.dao;

import com.shop.sphere.models.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Buyer} entities.
 * <p>
 * Provides CRUD operations on Buyer entities.
 * </p>
 */
@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {

    Buyer findBuyerByEmailAndPassword(String email, String password);

    Buyer findBuyerByEmail(String email);

    Optional<Buyer> findByEmail(String email);
}
