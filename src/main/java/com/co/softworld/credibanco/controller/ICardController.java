package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICardController {
    ResponseEntity<Card> generateCard(int productId);
    ResponseEntity<List<Card>> findAll();
    ResponseEntity<Card> delete (int cardId);
}
