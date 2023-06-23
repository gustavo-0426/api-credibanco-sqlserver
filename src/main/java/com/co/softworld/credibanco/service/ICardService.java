package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Card;
import org.springframework.http.ResponseEntity;

public interface ICardService {
    ResponseEntity<Card> generate(String productId);
}
