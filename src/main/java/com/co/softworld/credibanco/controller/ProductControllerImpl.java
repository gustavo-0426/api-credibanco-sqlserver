package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements IProductController {

    @Autowired
    private IProductService productService;

    @Override
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @Override
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable String productId) {
        return productService.findById(productId);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return productService.findAll();
    }

    @Override
    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> delete(@PathVariable String productId) {
        return productService.delete(productId);
    }

    @Override
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable String productId, @RequestBody Product product) {
        return productService.update(productId, product);
    }
}
