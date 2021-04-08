package com.greyfox.GreyFox.repositories;

import com.greyfox.GreyFox.domain.Transaction;
import com.greyfox.GreyFox.exceptions.ETBadRequestException;
import com.greyfox.GreyFox.exceptions.ETResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException;

    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws ETBadRequestException;

    void update(Integer userId, Integer categoryId, Transaction transaction) throws ETBadRequestException;

    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException;

}
