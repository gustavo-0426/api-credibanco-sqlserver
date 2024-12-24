package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.service.ICardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static java.util.List.of;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ICardController.class)
class CardControllerImplTest {

    @Autowired
    private MockMvc cardController;
    @MockBean
    private ICardService cardServiceMock;
    private String jsonCard;
    private Card card;
    private ResponseEntity<Card>  cardResponseEntity;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        Product product = new Product();
        card = new Card();
        card.setCardId(1);
        card.setNumber("1000001234567890");
        card.setProduct(product);
        card.setExpiryDate("06/2026");
        card.setBalance(0);
        card.setActive(0);
        cardResponseEntity = new ResponseEntity<>(card, OK);
        jsonCard = new ObjectMapper().writeValueAsString(card);
    }

    @AfterEach
    void tearDown() {
        cardController = null;
        cardServiceMock = null;
        jsonCard = null;
        card = null;
        cardResponseEntity = null;
    }

    @Test
    void testGenerateCard() throws Exception {
        when(cardServiceMock.generateCard(100000)).thenReturn(cardResponseEntity);
        cardController.perform(post("/card/100000/number")
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(jsonCard))
                .andExpect(status().isOk());
    }

    @Test
    void testActivateCard() throws Exception {
        card.setActive(1);
        jsonCard = new ObjectMapper().writeValueAsString(card);
        when(cardServiceMock.activateCard(card)).thenReturn(new ResponseEntity<>(card, OK));
        cardController.perform(post("/card/enroll")
                        .contentType(APPLICATION_JSON)
                        .content(jsonCard))
                .andExpect(jsonPath("$.active").value(1))
                .andExpect(status().isOk());
    }

    @Test
    void testBlock() throws Exception {
        when(cardServiceMock.block(1)).thenReturn(cardResponseEntity);
        cardController.perform(delete("/card/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.active").value(0))
                .andExpect(status().isOk());
    }

    @Test
    void testAddBalance() throws Exception {
        when(cardServiceMock.addBalance(card)).thenReturn(cardResponseEntity);
        cardController.perform(post("/card/balance")
                        .contentType(APPLICATION_JSON)
                        .content(jsonCard))
                .andExpect(content().json(jsonCard))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBalance() throws Exception {
        Map<String, Double> map = new HashMap<>();
        map.put("balance", 0.0);
        when(cardServiceMock.getBalance(1)).thenReturn(new ResponseEntity<>(map, OK));
        cardController.perform(get("/card/balance/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(0.0))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAll() throws Exception {
        when(cardServiceMock.findAll()).thenReturn(new ResponseEntity<>(of(card), OK));
        cardController.perform(get("/card")
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(of(card))))
                .andExpect(status().isOk());
    }
}