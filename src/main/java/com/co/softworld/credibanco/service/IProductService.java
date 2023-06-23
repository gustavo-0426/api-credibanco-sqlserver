package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    ResponseEntity<Product> save (Product product);
    ResponseEntity<Product> findById (String productId);
    ResponseEntity<List<Product>> findAll();
    ResponseEntity<Product> delete (String productId);
    ResponseEntity<Product> update (String productId, Product product);
}
