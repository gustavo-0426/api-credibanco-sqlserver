package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.exception.InvalidCardException;
import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.repository.ICardRepository;
import com.co.softworld.credibanco.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.co.softworld.credibanco.util.IUtility.*;
import static java.lang.Math.random;
import static java.lang.String.format;
import static java.time.LocalDate.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    private ICardRepository cardRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<Card> generateCard(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new InvalidCardException(PRODUCT_NOT_FOUND);
        Product product = optionalProduct.get();
        Card card = new Card();
        card.setNumber(generateNumber(productId));
        card.setProduct(product);
        card.setExpiryDate(now().plusYears(3).format(FORMAT_DATE));
        return new ResponseEntity<>(cardRepository.save(card), OK);
    }

    @Override
    public String generateNumber(int productId) {
        long random = (long)(random() * 10000000000L);
        return format("%s%10d", productId, random);
    }

    @Override
    public ResponseEntity<Card> activateCard(Card card) {
        Optional<Card> optionalCard = cardRepository.findById(card.getCardId());
        if (optionalCard.isEmpty())
            throw new InvalidCardException(CARD_NOT_FOUND);
        Card cardActivate = optionalCard.get();
        if (cardActivate.getActive() == 1)
            throw new InvalidCardException(CARD_IS_ACTIVE);
        cardActivate.setActive(1);
        return new ResponseEntity<>(cardRepository.save(cardActivate), NOT_FOUND);
    }

    @Override
    public ResponseEntity<Card> block(int cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty())
            throw new InvalidCardException(CARD_NOT_FOUND);
        Card cardBlock = optionalCard.get();
        if (cardBlock.getActive() == 0)
            throw new InvalidCardException(CARD_IS_INACTIVE);
        cardBlock.setActive(0);
        return new ResponseEntity<>(cardRepository.save(cardBlock), OK);
    }

    @Override
    public ResponseEntity<Card> addBalance(Card card) {
        Optional<Card> optionalCard = cardRepository.findById(card.getCardId());
        if (optionalCard.isEmpty())
            throw new InvalidCardException(CARD_NOT_FOUND);
        if (card.getBalance() <= 0)
            throw new InvalidCardException(CARD_INVALID_BALANCE);
        Card cardBalance = optionalCard.get();
        cardBalance.setBalance(cardBalance.getBalance() + card.getBalance());
        return new ResponseEntity<>(cardRepository.save(cardBalance), OK);
    }

    @Override
    public ResponseEntity<Map<String, Double>> getBalance(int cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty())
            throw new InvalidCardException(CARD_NOT_FOUND);
        Map<String, Double> balance = new HashMap<>();
        balance.put("balance", optionalCard.get().getBalance());
        return new ResponseEntity<>(balance, OK);
    }

    @Override
    public ResponseEntity<List<Card>> findAll() {
        return new ResponseEntity<>(cardRepository.findAll(), OK);
    }

}
