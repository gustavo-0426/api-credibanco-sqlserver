package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.TransactionManager;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITransactionController {
    ResponseEntity<TransactionManager> purchase(TransactionManager transaction);
    ResponseEntity<TransactionManager> getPurchase(int transactionId);
    ResponseEntity<TransactionManager> annulation(TransactionManager transaction);
    ResponseEntity<List<TransactionManager>> findAll();
    ResponseEntity<TransactionManager> delete(int transactionId);
}
