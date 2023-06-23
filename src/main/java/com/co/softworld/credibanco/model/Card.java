package com.co.softworld.credibanco.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String number;
    private String name;
    private Date date;
}
