package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardControllerImpl implements ICardController {

    @Autowired
    private ICardService cardService;

    @Override
    @GetMapping("/{productId}/number")
    public ResponseEntity<Card> generate(@PathVariable String productId) {
        return cardService.generate(productId);
    }
}
