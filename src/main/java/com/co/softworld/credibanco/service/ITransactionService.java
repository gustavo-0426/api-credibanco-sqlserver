package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.MapperTransaction;
import com.co.softworld.credibanco.model.TransactionManager;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITransactionService {
    ResponseEntity<TransactionManager> purchase(MapperTransaction mapper);
    ResponseEntity<TransactionManager> getPurchase(int transactionId);
    ResponseEntity<TransactionManager> annulation(MapperTransaction mapper);
    ResponseEntity<List<TransactionManager>> findAll();
}
