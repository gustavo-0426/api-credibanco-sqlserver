package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/enroll")
    public ResponseEntity<Card> activateCard(@Valid @RequestBody Card card) {
        return cardService.activateCard(card);
    }

    @Override
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Card> block(@PathVariable int cardId) {
        return cardService.block(cardId);
    }

    @Override
    @PostMapping("/balance")
    public ResponseEntity<Card> addBalance(@Valid @RequestBody Card card) {
        return cardService.addBalance(card);
    }

    @Override
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<Map<String, Double>> getBalance(@PathVariable int cardId) {
        return cardService.getBalance(cardId);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        return cardService.findAll();
    }

}
