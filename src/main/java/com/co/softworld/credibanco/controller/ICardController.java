package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICardController {
    ResponseEntity<Card> generateCard(int productId);
    ResponseEntity<Card> activateCard(Card card);
    ResponseEntity<Card> block(int cardId);
    ResponseEntity<Card> addBalance(Card card);
    ResponseEntity<Map<String, Double>> getBalance(int cardId);
    ResponseEntity<List<Card>> findAll();

}
