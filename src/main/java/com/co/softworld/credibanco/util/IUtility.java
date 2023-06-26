package com.co.softworld.credibanco.util;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public interface IUtility {
    DateTimeFormatter FORMAT_DATE = ofPattern("MM/yyyy");
    DateTimeFormatter FORMAT_DATETIME = ofPattern("dd-MM-yyyy HH:mm:ss");
    String VALID_NAME = "^[\\w]+\\s*[\\w]*";
    String CARD_IS_INACTIVE = "card is not activated";
    String CARD_IS_ACTIVE = "card is activated";
    String CARD_INVALID_BALANCE = "balance cannot be less than or equal to zero";
    String CARD_NOT_FOUND = "card not found";
    String PRODUCT_NOT_FOUND = "Product not found";
    String TRANSACTION_NOT_FOUND = "Transaction not found";
    String ANNULLED_INVALID = "Transaction date greater than 24 hours";
    String DECLINED_TRANSACTION = "declined transaction:";
    String TRANSACTION_INVALID_LOCKED_CARD = "declined transaction: card is not activated";
    String TRANSACTION_INVALID_EXPIRY_CARD = "declined transaction: expired card";
    String TRANSACTION_INVALID_BALANCE = "declined transaction: the transaction price is less than or equal to zero or the card has no balance";
    String TRANSACTION_INVALID_INSUFFICIENT_BALANCE = "declined transaction: insufficient balance";
    String ACTIVE = "Active";
    String ANNULLED = "Annulled";
    String EMPTY = "";
}
