package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
}
