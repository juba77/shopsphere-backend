package com.shop.sphere.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    @Size(max = 50, min = 3, message = "First name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "Invalid characters in first name")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(max = 50, min = 3, message = "Last name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "Invalid characters in last name")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address", regexp = "^([a-zA-Z0-9+_.-]+)@([a-zA-Z0-9.-]+)(\\.)([a-z]{2,3})$")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Address is required")
    @Size(max = 150, min = 3, message = "Address must be between 3 and 50 characters")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();

}
