package com.co.softworld.credibanco.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class TransactionManager {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private LocalDateTime date;
    private int cardId;
    private double price;
}
