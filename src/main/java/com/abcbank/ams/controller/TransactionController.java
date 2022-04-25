package com.abcbank.ams.controller;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.common.exception.AmsException;
import com.abcbank.ams.model.TransactionDetails;
import com.abcbank.ams.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/transaction")
@SecurityRequirement(name = "authorize")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<TransactionDetails>> getTransactionsByDateRange(@PathVariable Long userId,
                                                  @RequestParam String from,
                                                  @RequestParam String to) throws AmsException, ParseException {
        if (userId == null) {
            throw new AmsException("ERR400", "Invalid User ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactions(userId,from,to));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDetails transactionDetails) {
        transactionService.createTransaction(transactionDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(AmsConstants.MESSAGE_TRANSACTION_DONE);
    }

}
