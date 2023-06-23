package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICardService {
    ResponseEntity<Card> generateCard(String productId);
    String generateNumber(String productId);

    ResponseEntity<List<Card>> findAll();
    ResponseEntity<Card> delete (String cardId);
}
