package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICardService {
    ResponseEntity<Card> generateCard(int productId);
    String generateNumber(int productId);
    ResponseEntity<Card> activateCard(Card card);

    ResponseEntity<List<Card>> findAll();
    ResponseEntity<Card> delete (int cardId);
}
