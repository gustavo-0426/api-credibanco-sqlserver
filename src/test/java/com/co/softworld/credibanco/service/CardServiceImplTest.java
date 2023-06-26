package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.repository.ICardRepository;
import com.co.softworld.credibanco.repository.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceImplTest {

    @MockBean
    private ICardRepository cardRepositoryMock;
    @MockBean
    private IProductRepository productRepositoryMock;
    @Autowired
    private ICardService cardService;

    @Test
    void generateCard() {
    }
/*
    @Test
    void generateNumber() {
    }

    @Test
    void activateCard() {
    }

    @Test
    void block() {
    }

    @Test
    void addBalance() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void findAll() {
    }*/
}