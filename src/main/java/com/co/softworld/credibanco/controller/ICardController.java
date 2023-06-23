package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import org.springframework.http.ResponseEntity;

public interface ICardController {
    ResponseEntity<Card> generate(String productId);
}
