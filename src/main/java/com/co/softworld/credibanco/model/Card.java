package com.co.softworld.credibanco.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import static com.co.softworld.credibanco.util.IUtility.MSG_NUMBER;
import static com.co.softworld.credibanco.util.IUtility.VALID_NUMBER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int cardId;
    @Pattern(regexp = VALID_NUMBER, message = MSG_NUMBER)
    private String number;
    @ManyToOne()
    private Product product;
    private String expiryDate;
    private double balance;
    private int active;
}
