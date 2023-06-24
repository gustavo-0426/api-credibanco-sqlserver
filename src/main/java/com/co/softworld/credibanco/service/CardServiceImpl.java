package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.repository.ICardRepository;
import com.co.softworld.credibanco.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.co.softworld.credibanco.util.IUtility.MM_YYYY;
import static java.lang.Math.random;
import static java.lang.String.format;
import static java.time.LocalDate.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    private ICardRepository cardRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<Card> generateCard(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            return new ResponseEntity<>(NOT_FOUND);
        Product product = optionalProduct.get();
        Card card = new Card();
        card.setNumber(generateNumber(productId));
        card.setCustomer(product.getCustomer());
        card.setExpiryDate(now().plusYears(3).format(MM_YYYY));
        return new ResponseEntity<>(cardRepository.save(card), OK);
    }

    @Override
    public String generateNumber(int productId) {
        long random = (long)(random() * 10000000000L);
        return format("%s%10d", productId, random);
    }

    @Override
    public ResponseEntity<Card> activateCard(Card card) {
        Optional<Card> optionalCard = cardRepository.findById(card.getCardId());
        if (optionalCard.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        Card cardActivate = optionalCard.get();
        cardActivate.setActive(1);
        return new ResponseEntity<>(cardRepository.save(cardActivate), NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Card>> findAll() {
        return new ResponseEntity<>(cardRepository.findAll(), OK);
    }

    @Override
    public ResponseEntity<Card> delete(int cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        cardRepository.delete(optionalCard.get());
        return new ResponseEntity<>(optionalCard.get(), OK);
    }


}
