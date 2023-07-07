package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICardRepository extends JpaRepository<Card, Integer> {
    @Query("select c from Card c where c.cardId = ?1 and c.active = 1")
    Optional<Card> findByCardIdActive(int id);
    @Query("select c from Card c where c.cardId = ?1 and c.active = 0")
    Optional<Card> findByCardIdInactive(int id);
}
