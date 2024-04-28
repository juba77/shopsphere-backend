package com.shop.sphere.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Represents a product in the ShopSphere web store.
 * <p>
 * Each product is defined by a photo, title, price, quantity, and description.
 * </p>
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Represents the URL of the product's photo.
     * <p>
     * Must be a valid URL and have certain allowed extensions (e.g., .jpg, .png).
     * </p>
     */
    @Column(nullable = false)
    @NotBlank(message = "Photo URL is required")
    @Pattern(regexp = "^(https?):\\/\\/[^\\s$.?#].[^\\s]*\\.(jpg|png)(\\\\?.*)?$", message = "Invalid photo URL or unsupported extension")
    private String photo;

    /**
     * Represents the title of the product.
     * <p>
     * Must not be blank, and its length should be between 3 and 100 characters.
     * Invalid characters are not allowed.
     * </p>
     */
    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    @Size(max = 250, min = 3, message = "Title must be between 3 and 250 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-]+$", message = "Invalid characters in title")
    private String title;

    /**
     * Represents the price of the product.
     * <p>
     * Must be a positive value, have a maximum allowed value,
     * and should be more than 0.
     * </p>
     */
    @Column(nullable = false)
    @Positive(message = "Price must be positive")
    @DecimalMax(value = "999999.99", message = "Price too high")
    private Double price;

    /**
     * Represents the quantity of the product available in stock.
     * <p>
     * Must be a non-negative integer.
     * </p>
     */
    @Column(nullable = false)
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    /**
     * Represents the description of the product.
     * <p>
     * Must not be blank and should have a minimum length.
     * </p>
     */
    @Column(nullable = false)
    @NotBlank(message = "Description is required")
    @Size(min = 3, message = "Description must be at least 3 characters long")
    private String description;

}
