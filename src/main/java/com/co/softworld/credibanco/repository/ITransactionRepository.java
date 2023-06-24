package com.co.softworld.credibanco.repository;

import com.co.softworld.credibanco.model.TransactionManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionManager, Integer> {
}
