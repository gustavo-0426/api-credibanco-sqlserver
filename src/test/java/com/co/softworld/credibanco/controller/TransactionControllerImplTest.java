package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Card;
import com.co.softworld.credibanco.model.TransactionMapper;
import com.co.softworld.credibanco.model.TransactionManager;
import com.co.softworld.credibanco.service.ITransactionService;
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

import static java.util.List.of;
import static com.co.softworld.credibanco.util.IUtility.ACTIVE;
import static com.co.softworld.credibanco.util.IUtility.ANNULLED;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ITransactionController.class)
class TransactionControllerImplTest {

    @Autowired
    private MockMvc transactionController;
    @MockBean
    private ITransactionService transactionService;
    private TransactionManager transaction;
    private TransactionMapper transactionMapper;
    private ResponseEntity<TransactionManager> transactionResponseEntity;
    private String transactionRequestJson;
    private String transactionResponseJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        transaction = new TransactionManager();
        transaction.setTransactionId(1);
        transaction.setDate("06/2026");
        transaction.setCard(new Card());
        transaction.setPrice(100);
        transaction.setStatus(ACTIVE);

        transactionMapper = new TransactionMapper();
        transactionMapper.setCardId(1);
        transactionMapper.setTransactionId(1);
        transactionMapper.setPrice(200);

        transactionResponseEntity = new ResponseEntity<>(transaction, OK);

        transactionRequestJson = new ObjectMapper().writeValueAsString(transactionMapper);
        transactionResponseJson = new ObjectMapper().writeValueAsString(transaction);
    }

    @AfterEach
    void tearDown() {
        transactionController = null;
        transactionService = null;
        transaction = null;
        transactionMapper = null;
        transactionResponseEntity = null;
        transactionRequestJson = null;
        transactionResponseJson = null;
    }

    @Test
    void testPurchase() throws Exception {
        when(transactionService.purchase(transactionMapper)).thenReturn(transactionResponseEntity);
        transactionController.perform(post("/transaction/purchase")
                .contentType(APPLICATION_JSON)
                .content(transactionRequestJson))
                .andExpect(content().json(transactionResponseJson))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPurchase() throws Exception {
        when(transactionService.getPurchase(1)).thenReturn(transactionResponseEntity);
        transactionController.perform(get("/transaction/1")
                .contentType(APPLICATION_JSON))
                .andExpect(content().json(transactionResponseJson))
                .andExpect(status().isOk());
    }

    @Test
    void testAnnulation() throws Exception {
        transaction.setStatus(ANNULLED);
        when(transactionService.annulation(transactionMapper)).thenReturn(new ResponseEntity<>(transaction, OK));
        transactionController.perform(post("/transaction/anulation")
                        .contentType(APPLICATION_JSON)
                        .content(transactionRequestJson))
                .andExpect(jsonPath("$.status").value(ANNULLED))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAll() throws Exception {
        when(transactionService.findAll()).thenReturn(new ResponseEntity<>(of(transaction), OK));
        transactionController.perform(get("/transaction")
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(of(transaction))))
                .andExpect(status().isOk());
    }
}