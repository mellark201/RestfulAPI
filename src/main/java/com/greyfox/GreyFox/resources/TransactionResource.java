package com.greyfox.GreyFox.resources;

import com.greyfox.GreyFox.domain.Category;
import com.greyfox.GreyFox.domain.Transaction;
import com.greyfox.GreyFox.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

}
