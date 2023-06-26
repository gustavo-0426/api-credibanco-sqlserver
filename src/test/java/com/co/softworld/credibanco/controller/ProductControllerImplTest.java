package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.service.IProductService;
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
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IProductController.class)
class ProductControllerImplTest {

    @Autowired
    private MockMvc productController;
    @MockBean
    private IProductService productService;
    private Product product;
    private String productJson;
    private ResponseEntity<Product> productResponseEntity;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        product = new Product();
        product.setProductId(100000);
        product.setName("Product Name");
        product.setCustomer("Gustavo Castro");
        productJson = new ObjectMapper().writeValueAsString(product);
        productResponseEntity = new ResponseEntity<>(product, OK);
    }

    @AfterEach
    void tearDown() {
        productController = null;
        productService = null;
        product = null;
        productJson = null;
        productResponseEntity = null;
    }

    @Test
    void testSave() throws Exception {
        when(productService.save(product)).thenReturn(productResponseEntity);
        productController.perform(post("/product")
                        .contentType(APPLICATION_JSON)
                        .content(productJson))
                .andExpect(content().json(productJson))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        when(productService.findById(100000)).thenReturn(productResponseEntity);
        productController.perform(get("/product/100000")
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(productJson))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAll() throws Exception {
        when(productService.findAll()).thenReturn(new ResponseEntity<>(of(product), OK));
        productController.perform(get("/product")
                .contentType(APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(of(product))))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(productService.delete(100000)).thenReturn(productResponseEntity);
        productController.perform(delete("/product/100000")
                .contentType(APPLICATION_JSON))
                .andExpect(content().json(productJson))
                .andExpect(status().isOk());
    }
}