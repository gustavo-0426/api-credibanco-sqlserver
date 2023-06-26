package com.co.softworld.credibanco.model;

import lombok.Data;

@Data
public class TransactionMapper {

    private int cardId;
    private int transactionId;
    private double price;
}
