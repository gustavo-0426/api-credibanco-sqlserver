package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    ResponseEntity<Product> save(Product product);
    ResponseEntity<Product> findById(int productId);
    ResponseEntity<List<Product>> findAll();
    ResponseEntity<Product> delete(int productId);
}
