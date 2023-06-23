package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICardRepository extends MongoRepository<Card, String> {
}
