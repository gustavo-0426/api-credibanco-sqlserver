package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductController {
    ResponseEntity<Product> save (Product product);
    ResponseEntity<Product> findById (int productId);
    ResponseEntity<List<Product>> findAll();
    ResponseEntity<Product> delete (int productId);
}
