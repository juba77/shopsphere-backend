package com.shop.sphere.dao;

import com.shop.sphere.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Admin} entities.
 * <p>
 * Provides CRUD operations on Admin entities.
 * </p>
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

    Admin findAdminByEmailAndPassword(String email, String password);

}
