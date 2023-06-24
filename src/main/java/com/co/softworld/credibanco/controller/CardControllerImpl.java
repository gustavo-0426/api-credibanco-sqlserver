package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardControllerImpl implements ICardController {

    @Autowired
    private ICardService cardService;

    @Override
    @GetMapping("/{productId}/number")
    public ResponseEntity<Card> generateCard(@PathVariable int productId) {
        return cardService.generateCard(productId);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        return cardService.findAll();
    }

    @Override
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Card> delete(@PathVariable int cardId) {
        return cardService.delete(cardId);
    }

}
