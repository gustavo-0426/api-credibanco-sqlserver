package com.co.softworld.credibanco.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    private String customer;
}