package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.exception.InvalidTransactionException;
import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.MapperTransaction;
import com.co.softworld.credibanco.model.TransactionManager;
import com.co.softworld.credibanco.repository.ICardRepository;
import com.co.softworld.credibanco.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.co.softworld.credibanco.util.IUtility.*;
import static java.lang.String.format;
import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static org.springframework.http.HttpStatus.*;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private ICardRepository cardRepository;

    @Override
    public ResponseEntity<TransactionManager> purchase(MapperTransaction mapper) {
        Card card = cardRepository.findById(mapper.getCardId()).orElse(null);
        double price = mapper.getPrice();
        String date = now().format(FORMAT_DATE);
        if (card == null)
            throw new InvalidTransactionException(format("%s %s", DECLINED_TRANSACTION, CARD_NOT_FOUND));
        if (card.getActive() != 1)
            throw new InvalidTransactionException(TRANSACTION_INVALID_LOCKED_CARD);
        if (date.compareTo(card.getExpiryDate()) > 0)
            throw new InvalidTransactionException(TRANSACTION_INVALID_EXPIRY_CARD);
        if (price <= 0 || card.getBalance() == 0)
            throw new InvalidTransactionException(TRANSACTION_INVALID_BALANCE);
        if (card.getBalance() < price)
            throw new InvalidTransactionException(TRANSACTION_INVALID_INSUFFICIENT_BALANCE);
        card.setBalance(card.getBalance() - price);
        cardRepository.save(card);
        TransactionManager transaction = new TransactionManager();
        transaction.setDate(LocalDateTime.now().format(FORMAT_DATETIME));
        transaction.setCard(card);
        transaction.setPrice(price);
        transaction.setStatus(ACTIVE);
        return new ResponseEntity<>(transactionRepository.save(transaction), OK);
    }

    @Override
    public ResponseEntity<TransactionManager> getPurchase(int transactionId) {
        Optional<TransactionManager> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isEmpty())
            throw new InvalidTransactionException(TRANSACTION_NOT_FOUND);
        return new ResponseEntity<>(optionalTransaction.get(), OK);
    }

    @Override
    public ResponseEntity<TransactionManager> annulation(MapperTransaction mapper) {
        Optional<TransactionManager> optionalTransaction = transactionRepository.findById(mapper.getTransactionId());
        if (optionalTransaction.isEmpty())
            throw new InvalidTransactionException(TRANSACTION_NOT_FOUND);
        TransactionManager transactionAnnulation = optionalTransaction.get();
        LocalTime timeTransaction = LocalDateTime.parse(transactionAnnulation.getDate(), FORMAT_DATETIME).toLocalTime();
        LocalTime timeNow = LocalTime.now();
        if (between(timeTransaction, timeNow).toSeconds() > 1440)
            throw new InvalidTransactionException(ANNULLED_INVALID);
        Card card = transactionAnnulation.getCard();
        card.setBalance(card.getBalance() + transactionAnnulation.getPrice());
        cardRepository.save(card);
        transactionAnnulation.setCard(card);
        transactionAnnulation.setStatus(ANNULLED);
        return new ResponseEntity<>(transactionRepository.save(transactionAnnulation), OK);
    }

    @Override
    public ResponseEntity<List<TransactionManager>> findAll() {
        return new ResponseEntity<>(transactionRepository.findAll(), OK);
    }
}
