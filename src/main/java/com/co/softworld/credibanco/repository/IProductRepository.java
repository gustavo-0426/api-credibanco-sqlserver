package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<Product, String> {
}
