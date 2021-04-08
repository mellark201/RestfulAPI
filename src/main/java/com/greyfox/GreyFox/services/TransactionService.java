package com.greyfox.GreyFox.services;

import com.greyfox.GreyFox.domain.Transaction;
import com.greyfox.GreyFox.exceptions.ETBadRequestException;
import com.greyfox.GreyFox.exceptions.ETResourceNotFoundException;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException;

    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws ETBadRequestException;

    void updateTransaction(Integer userId, Integer categoryId, Transaction transaction) throws ETBadRequestException;

    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws ETBadRequestException;

}
