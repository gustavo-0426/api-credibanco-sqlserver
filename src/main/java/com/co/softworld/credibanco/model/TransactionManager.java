package com.co.softworld.credibanco.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "transaction_mg")
@Data
public class TransactionManager {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int transactionId;
    private String date;
    @ManyToOne()
    private Card card;
    private double price;
    private String status;
}
