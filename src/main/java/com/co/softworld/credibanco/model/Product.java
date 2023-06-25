package com.co.softworld.credibanco.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.co.softworld.credibanco.util.IUtility.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY, generator = "generate_sequence")
    @SequenceGenerator(name = "generate_sequence", sequenceName = "sequence", allocationSize = 1, initialValue = 99999)
    private int productId;

    @NotNull(message = "The name field must not have a null value")
    @Pattern(regexp = VALID_NAME, message = "The name field must not have special characters or be empty")
    private String name;

    @NotNull(message = "The customer field must not have a null value")
    @Pattern(regexp = VALID_NAME, message = "The customer field must not have special characters or be empty")
    private String customer;
}