package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.TransactionMapper;
import com.co.softworld.credibanco.model.TransactionManager;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITransactionController {
    ResponseEntity<TransactionManager> purchase(TransactionMapper mapper);
    ResponseEntity<TransactionManager> getPurchase(int transactionId);
    ResponseEntity<TransactionManager> annulation(TransactionMapper mapper);
    ResponseEntity<List<TransactionManager>> findAll();
}
