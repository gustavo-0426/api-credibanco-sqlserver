package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements IProductController {

    @Autowired
    private IProductService productService;

    @Override
    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @Override
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable int productId) {
        return productService.findById(productId);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return productService.findAll();
    }

    @Override
    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> delete(@PathVariable int productId) {
        return productService.delete(productId);
    }

}