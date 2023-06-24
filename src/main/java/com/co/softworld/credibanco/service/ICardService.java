package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICardService {
    ResponseEntity<Card> generateCard(int productId);
    String generateNumber(int productId);
    ResponseEntity<Card> activateCard(Card card);
    ResponseEntity<Card> block(int cardId);
    ResponseEntity<Card> addBalance(Card card);
    ResponseEntity<Double> getBalance(int cardId);

    ResponseEntity<List<Card>> findAll();

}
