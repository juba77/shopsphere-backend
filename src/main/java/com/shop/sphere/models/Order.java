package com.shop.sphere.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents an order in the ShopSphere web store.
 * <p>
 * Each order is defined by a number, list of products, date, finalized status, and a buyer.
 * </p>
 */
@Data
@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Represents the unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Represents the list of products associated with the order.
     */
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> articles;

    /**
     * Represents the date when the order was placed.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * Indicates whether the order is finalized or not.
     */
    @Column(nullable = false)
    private Boolean isFinalized;

    /**
     * Represents the buyer who placed the order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    @JsonBackReference
    private Buyer client;

}
