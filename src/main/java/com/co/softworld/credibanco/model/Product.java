package com.co.softworld.credibanco.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @NotNull
    private String name;
    private String customer;
}