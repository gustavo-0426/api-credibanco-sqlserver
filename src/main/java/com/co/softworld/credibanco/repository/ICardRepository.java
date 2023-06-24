package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<Card, Integer> {
}
