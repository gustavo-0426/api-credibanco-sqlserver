package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.MapperTransaction;
import com.co.softworld.credibanco.model.TransactionManager;
import com.co.softworld.credibanco.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionControllerImpl implements ITransactionController {

    @Autowired
    private ITransactionService transactionService;

    @Override
    @PostMapping("/purchase")
    public ResponseEntity<TransactionManager> purchase(@Valid @RequestBody MapperTransaction mapper) {
        return transactionService.purchase(mapper);
    }

    @Override
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionManager> getPurchase(@PathVariable int transactionId) {
        return transactionService.getPurchase(transactionId);
    }

    @Override
    @PostMapping("/anulation")
    public ResponseEntity<TransactionManager> annulation(@Valid @RequestBody MapperTransaction mapper) {
        return transactionService.annulation(mapper);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TransactionManager>> findAll() {
        return transactionService.findAll();
    }

    @Override
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<TransactionManager> delete(@PathVariable int transactionId) {
        return transactionService.delete(transactionId);
    }
}
