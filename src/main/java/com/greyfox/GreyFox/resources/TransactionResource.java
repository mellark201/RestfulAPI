package com.greyfox.GreyFox.resources;

import com.greyfox.GreyFox.domain.Category;
import com.greyfox.GreyFox.domain.Transaction;
import com.greyfox.GreyFox.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{category_id}/transactions")

public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("category_id") Integer categoryId, 
                                                      @RequestBody Map<String, Object> transactionMap) {
        int userId = (Integer) request.getAttribute("userId");
        String note = (String) request.getAttribute("note");
        double amount = (Double) request.getAttribute("amount");
        long timeStamp = (Long) request.getAttribute("timeStamp");
        Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, timeStamp);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/{transaction_id}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("transaction_id") Integer transactionId,
                                                          @PathVariable("category_id") Integer categoryId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransaction(HttpServletRequest request,
                                                               @PathVariable("category_Id") Integer categoryId) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<Transaction> transactionList = transactionService.fetchAllTransactions(userId, categoryId);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);

    }

    @PutMapping("/{transaction_id}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request, @PathVariable(
            "category_id") Integer categoryId, @PathVariable("transaction_id") Integer transactionId,
                                                                  @RequestBody Transaction transaction) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.updateTransaction(userId, categoryId, transactionId, transaction);
        Map<String, Boolean> map = new HashMap<>();
        map.put("message", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{transaction_id}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request, @PathVariable(
            "category_id") Integer categoryId, @PathVariable("transaction_id") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.removeTransaction(userId, categoryId, transactionId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
