package com.co.softworld.credibanco.service;

import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<Product> save(Product product) {
        return new ResponseEntity<>(productRepository.save(product), OK);
    }

    @Override
    public ResponseEntity<Product> findById(int productId) {
        return new ResponseEntity<>(productRepository.findById(productId).orElse(null), OK);
    }

    @Override
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productRepository.findAll(), OK);
    }

    @Override
    public ResponseEntity<Product> delete(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        productRepository.delete(optionalProduct.get());
        return new ResponseEntity<>(optionalProduct.get(), OK);
    }

    @Override
    public ResponseEntity<Product> update(int productId, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            return new ResponseEntity<>(null, NOT_FOUND);
        Product productUpdate = optionalProduct.get();
        productUpdate.setName(product.getName());
        return new ResponseEntity<>(productRepository.save(productUpdate), OK);
    }
}
