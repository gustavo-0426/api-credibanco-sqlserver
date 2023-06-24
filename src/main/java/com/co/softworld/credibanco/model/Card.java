package com.co.softworld.credibanco.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String number;
    private String name;
    private String date;
}
