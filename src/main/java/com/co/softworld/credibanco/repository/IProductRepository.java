package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
