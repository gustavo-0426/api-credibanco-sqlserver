package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.TransactionManager;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITransactionService {
    ResponseEntity<TransactionManager> purchase(TransactionManager transaction);
    ResponseEntity<TransactionManager> getPurchase(int transactionId);
    ResponseEntity<List<TransactionManager>> findAll();
    ResponseEntity<TransactionManager> delete(int transactionId);
}
