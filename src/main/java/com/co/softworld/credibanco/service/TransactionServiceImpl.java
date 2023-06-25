package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.TransactionManager;
import com.co.softworld.credibanco.repository.ICardRepository;
import com.co.softworld.credibanco.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.co.softworld.credibanco.util.IUtility.FORMAT_DATE;
import static java.lang.Double.sum;
import static java.time.LocalDate.now;
import static org.springframework.http.HttpStatus.*;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private ICardRepository cardRepository;

    @Override
    public ResponseEntity<TransactionManager> purchase(TransactionManager transaction) {
        Optional<Card> optionalCard = cardRepository.findById(transaction.getCardId());
        if (optionalCard.isEmpty() || optionalCard.get().getActive() != 1)
            return new ResponseEntity<>(null, NOT_FOUND);
        Card card = optionalCard.get();
        double price = transaction.getPrice();
        String date = now().format(FORMAT_DATE);
        if (date.compareTo(card.getExpiryDate()) > 0)
            return new ResponseEntity<>(null, BAD_REQUEST);
        if (price == 0 || card.getBalance() == 0 || card.getBalance() < price)
            return new ResponseEntity<>(null, BAD_REQUEST);
        card.setBalance(card.getBalance() - price);
        cardRepository.save(card);
        transaction.setDate(LocalDateTime.now());
        transaction.setStatus("Active");
        return new ResponseEntity<>(transactionRepository.save(transaction), OK);
    }

    @Override
    public ResponseEntity<TransactionManager> getPurchase(int transactionId) {
        Optional<TransactionManager> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        return new ResponseEntity<>(optionalTransaction.get(), OK);
    }

    @Override
    public ResponseEntity<TransactionManager> annulation(TransactionManager transaction) {
        Optional<TransactionManager> optionalTransaction = transactionRepository.findById(transaction.getTransactionId());
        if (optionalTransaction.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        TransactionManager transactionAnnulation = optionalTransaction.get();
        Optional<Card> optionalCard = cardRepository.findById(transactionAnnulation.getCardId());
        if (optionalCard.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        Card card = optionalCard.get();
        card.setBalance(sum(card.getBalance(), transactionAnnulation.getPrice()));
        cardRepository.save(card);
        transactionAnnulation.setStatus("Annulled");
        return new ResponseEntity<>(transactionRepository.save(transactionAnnulation), OK);
    }

    @Override
    public ResponseEntity<List<TransactionManager>> findAll() {
        return new ResponseEntity<>(transactionRepository.findAll(), OK);
    }

    @Override
    public ResponseEntity<TransactionManager> delete(int transactionId) {
        Optional<TransactionManager> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        transactionRepository.delete(optionalTransaction.get());
        return new ResponseEntity<>(optionalTransaction.get(), OK);
    }
}
